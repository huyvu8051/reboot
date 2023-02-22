package io.huyvu.reboot.backend.auth.google.v1;

public interface GoogleOAuthService {
    GoogleOAccountToken extractToken(String token) throws GoogleOAuthException;
}
