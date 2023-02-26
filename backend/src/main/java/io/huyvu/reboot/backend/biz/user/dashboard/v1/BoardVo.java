package io.huyvu.reboot.backend.biz.user.dashboard.v1;

public record BoardVo(long id,
                      String name,
                      String backgroundImage,
                      boolean stared,
                      long wpId) {
}
