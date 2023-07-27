package io.huyvu.reboot.security.model;

public record AccountTokenVo(String email,
                             boolean emailVerified,
                             String name,
                             String pictureUrl,
                             String locale,
                             String familyName,
                             String givenName) {
}
