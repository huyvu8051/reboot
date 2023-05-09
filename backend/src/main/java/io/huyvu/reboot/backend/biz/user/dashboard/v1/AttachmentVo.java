package io.huyvu.reboot.backend.biz.user.dashboard.v1;

import java.time.LocalDateTime;

public record AttachmentVo(long id, long cId, String name, String type, LocalDateTime createdDate) {
}
