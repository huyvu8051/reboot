package io.huyvu.reboot.security.model;

public record UserAccount(long id,
                          String username,
                          String fullName,
                          String pictureUrl) {
}
