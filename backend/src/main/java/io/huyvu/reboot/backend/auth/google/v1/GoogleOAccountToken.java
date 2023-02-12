package io.huyvu.reboot.backend.auth.google.v1;

public record GoogleAccountToken(String email, boolean emailVerified, String name, String pictureUrl, String locale,
                                 String familyName, String givenName) {
}
