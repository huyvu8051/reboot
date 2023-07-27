package io.huyvu.reboot.user.dashboard;

import java.util.Date;

public record AttachmentVo(long id, long cId, String name, String fileNm, String type, Date createdDate) {
}
