package io.huyvu.reboot.backend.auth.google.v1;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GoogleAuthResp {
    private String token;
    private String username;
    private String fullName;

}
