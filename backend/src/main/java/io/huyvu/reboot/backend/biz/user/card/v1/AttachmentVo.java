package io.huyvu.reboot.backend.biz.user.card.v1;

import java.util.Date;

public record AttachmentVo(long id, long cId, String name,String fileNm, String type, Date createdDate) {
}
