package io.huyvu.reboot.backend.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
@Slf4j
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException {
        log.info(this.getClass().getName() + ": " + req.getRequestURI());

        resp.setHeader("Content-Type", "application/json");


        OutputStream out = resp.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();



        mapper.writeValue(out, "Unauthorized");
        out.flush();
    }
}