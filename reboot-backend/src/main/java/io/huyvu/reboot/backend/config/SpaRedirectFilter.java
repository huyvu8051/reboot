package io.huyvu.reboot.backend.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@Slf4j
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SpaRedirectFilter extends OncePerRequestFilter {

    private final String REGEX = "(?!/actuator|/api|/resources|/socket\\.io|/h2-console|/_nuxt|/static|/assets|/index\\.html|/200\\.html|/manifest\\.json|/favicon\\.ico|/sw\\.js).*$";
    private Pattern pattern = Pattern.compile(REGEX);
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        log.info("URL {} entered directly into the Browser, redirecting...", req.getRequestURI());
        RequestDispatcher rd = req.getRequestDispatcher("/");
        rd.forward(req, res);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest req) {
        return !pattern.matcher(req.getRequestURI()).matches() || req.getRequestURI().equals("/");
    }
}