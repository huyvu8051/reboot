package io.huyvu.reboot.backend.authenticate;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuthenticateReq {
    private String username;
    private String password;
}
