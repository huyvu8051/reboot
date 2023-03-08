package io.huyvu.reboot.backend.config.security.google.v1;

public record UserAccount(long id,
                          String username,
                          String fullName,
                          String password,
                          String pictureUrl) {
}
