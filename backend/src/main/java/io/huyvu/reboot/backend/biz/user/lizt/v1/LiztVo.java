package io.huyvu.reboot.backend.biz.user.lizt.v1;

public record LiztVo(long id,
                     long boardId,
                     long wpId,
                     int ordinal,
                     String title) {
}
