package io.huyvu.reboot.security.service;

import io.huyvu.reboot.security.exception.AuthException;
import io.huyvu.reboot.security.model.AccountTokenVo;

public interface GoogleAuthenticationService {
    AccountTokenVo extractToken(String token) throws AuthException;
}
