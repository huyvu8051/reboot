package io.huyvu.reboot.backend.authenticate;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuthenticateResp {
    private String token;
    private String username;
    private String fullName;

}
