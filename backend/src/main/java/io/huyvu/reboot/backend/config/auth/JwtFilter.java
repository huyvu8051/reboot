package io.huyvu.reboot.backend.config.auth;

import io.huyvu.reboot.backend.util.AuthorityUtils;
import io.huyvu.reboot.backend.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private static final String REFRESH_TOKEN_KEY = "refreshToken";
    private final JwtUtils jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = req.getHeader("Authorization");

        String jwtToken = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwtToken = authorizationHeader.substring(7);
        }

        if (jwtToken != null && !jwtToken.isBlank() && !jwtToken.isEmpty() && jwtUtil.validateToken(jwtToken) && SecurityContextHolder.getContext().getAuthentication() == null) {

            long userId = jwtUtil.extractUserId(jwtToken);
            String username = jwtUtil.extractUsername(jwtToken);
            List<String> roles = jwtUtil.extractRoles(jwtToken);
            List<GrantedAuthority> authorities = AuthorityUtils.toAuthorities(roles);

            UserContextVo userCtx = new UserContextVo(userId, username);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userCtx, null, authorities);
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            // refresh token
            if (jwtUtil.tokenMustRefresh(jwtToken)) {
                String refreshToken = jwtUtil.generateJwtToken(userId, username, roles);
                resp.addHeader(REFRESH_TOKEN_KEY, refreshToken);
            }
        }
        filterChain.doFilter(req, resp);
    }
}