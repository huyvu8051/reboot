package io.huyvu.reboot.backend.config.security.google.v1;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class AuthRes {
    private String token;
    private String username;
    private String fullName;
    private String pictureUrl;
    List<String> roles;

}
