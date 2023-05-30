package io.huyvu.reboot.backend.biz.user.dashboard.v1;

import java.time.LocalDateTime;
import java.util.Date;

public record AttachmentVo(long id, long cId, String name, String fileNm, String type, Date createdDate) {
}
