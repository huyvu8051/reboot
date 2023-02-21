package io.huyvu.reboot.backend.auth.google.v1

import lombok.Builder
import lombok.Getter

import java.util.List

@Builder
@Getter
public class AuthResp {
    private String token
    private String username
    private String fullName
    private String pictureUrl
    List<String> roles

}
