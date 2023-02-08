package io.huyvu.reboot.backend.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
@Slf4j
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp, AccessDeniedException e) throws IOException {

        log.warn(req.getRequestURI());

        OutputStream out = resp.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        HttpStatus forbidden = HttpStatus.FORBIDDEN;

        resp.setHeader("Content-Type", "application/json");


        mapper.writeValue(out, "Access denied");
        out.flush();
    }
}