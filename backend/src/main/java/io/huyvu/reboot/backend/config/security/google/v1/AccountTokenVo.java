package io.huyvu.reboot.backend.config.security.google.v1;

public record AccountTokenVo(String email,
                             boolean emailVerified,
                             String name,
                             String pictureUrl,
                             String locale,
                             String familyName,
                             String givenName) {
}
