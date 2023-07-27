package io.huyvu.reboot.user.dashboard;

public record BoardVo(long id,
                      String name,
                      String backgroundImage,
                      boolean stared,
                      long wpId) {
}
