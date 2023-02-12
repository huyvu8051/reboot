package io.huyvu.reboot.backend.auth.google.v1;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GoogleAuthReq {
    private String idToken;
}
