package io.huyvu.reboot.backend.auth.google.v1;

public record GoogleOAccountToken(String email, boolean emailVerified, String name, String pictureUrl, String locale,
                                  String familyName, String givenName) {
}
