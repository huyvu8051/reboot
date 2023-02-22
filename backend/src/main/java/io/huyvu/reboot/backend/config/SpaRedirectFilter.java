package io.huyvu.reboot.backend.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
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
public class SpaRedirectFilter extends OncePerRequestFilter {

    // Forwards all routes except '/index.html', '/200.html', '/favicon.ico', '/sw.js' '/api/', '/api/**'
    private final String REGEX = "(?!/actuator|/api|/h2-console|/_nuxt|/static|/assets|/index\\.html|/200\\.html|/favicon\\.ico|/sw\\.js).*$";
    private Pattern pattern = Pattern.compile(REGEX);
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        // log.warn(req.getRequestURI());
        if (pattern.matcher(req.getRequestURI()).matches() && !req.getRequestURI().equals("/")) {
            // Delegate/Forward to `/` if `pattern` matches and it is not `/`
            // Required because of 'mode: history'usage in frontend routing, see README for further details
            log.info("URL {} entered directly into the Browser, redirecting...", req.getRequestURI());
            RequestDispatcher rd = req.getRequestDispatcher("/");
            rd.forward(req, res);
        } else {
            chain.doFilter(req, res);
        }

    }
}