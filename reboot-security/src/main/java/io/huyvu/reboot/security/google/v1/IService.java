package io.huyvu.reboot.security.google.v1;

public interface IService {
    AccountTokenVo extractToken(String token) throws AuthException;
}
