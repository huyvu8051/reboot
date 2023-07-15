package io.huyvu.reboot.security.google.v1;

public record UserAccount(long id,
                          String username,
                          String fullName,
                          String pictureUrl) {
}
