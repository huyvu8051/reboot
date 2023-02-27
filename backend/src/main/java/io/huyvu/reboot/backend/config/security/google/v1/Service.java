package io.huyvu.reboot.backend.config.security.google.v1;

public interface Service {
    AccountTokenVo extractToken(String token) throws AuthException;
}
