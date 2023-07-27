package io.huyvu.reboot.backend.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({LockedException.class, BadCredentialsException.class})
    ResponseEntity handleAuthenticationException(Exception e) {
        log.debug("handleAuthenticationException" + e.getMessage());
        return new ResponseEntity<>("Auth ex", BAD_REQUEST);
    }


    @ExceptionHandler({Exception.class})
    ResponseEntity handleUnhandledException(Exception e) {
        log.error("Unhandled: " + e.getMessage());
        return new ResponseEntity<>("Fatal ex", BAD_REQUEST);
    }

}