package io.huyvu.reboot.backend.biz.user.wp.v1;

public record UserAccount(long id,
                          String username,
                          String fullName,
                          String pictureUrl) {
}
