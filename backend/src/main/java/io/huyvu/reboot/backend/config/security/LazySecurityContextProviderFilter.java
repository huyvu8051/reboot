package io.huyvu.reboot.backend.config.security;

import io.huyvu.reboot.backend.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class LazySecurityContextProviderFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
            throws ServletException, IOException {

        var context = SecurityContextHolder.getContext();
        SecurityContextHolder.setContext(new LazyJwtSecurityContextProvider(req, res, context));
        filterChain.doFilter(req, res);
    }

    static class LazyJwtSecurityContextProvider implements SecurityContext {
        private static final String REFRESH_TOKEN_KEY = "REFRESH_TOKEN";
        private final SecurityContext securityCtx;

        private final HttpServletRequest req;
        private final HttpServletResponse res;

        LazyJwtSecurityContextProvider(HttpServletRequest req, HttpServletResponse res, SecurityContext securityCtx) {
            this.securityCtx = securityCtx;
            this.req = req;
            this.res = res;
        }


        @Override
        public Authentication getAuthentication() {

            if (securityCtx.getAuthentication() == null || securityCtx.getAuthentication() instanceof AnonymousAuthenticationToken) {
                try {
                    String authorizationHeader = req.getHeader("Authorization");
                    Assert.isTrue(authorizationHeader.startsWith("Bearer "), "Authorization header must start with 'Bearer '.");

                    String jwtToken = authorizationHeader.substring(7);

                    var userCtx = SecurityUtils.validateJWTToken(jwtToken);
                    var authToken = new PreAuthenticatedAuthenticationToken(userCtx, jwtToken, userCtx.roles());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                    securityCtx.setAuthentication(authToken);

                    // refresh token
                    if (userCtx.mustRefresh()) {
                        String refreshToken = SecurityUtils.generateJwtToken(userCtx.uId(), userCtx.username(), userCtx.roles());
                        res.addHeader(REFRESH_TOKEN_KEY, refreshToken);
                    }

                } catch (Exception e) {
                    log.debug("Cannot get authentication context: " + e.getMessage());
                }

            }

            return securityCtx.getAuthentication();
        }

        @Override
        public void setAuthentication(Authentication authentication) {
            securityCtx.setAuthentication(authentication);
        }
    }


}