package io.huyvu.reboot.biz.wp.model;

public record UserAccount(long id,
                          String username,
                          String fullName,
                          String pictureUrl) {
}
