package io.huyvu.reboot.user.card;

import java.util.Date;

public record AttachmentVo(long id, long cId, String name,String fileNm, String type, Date createdDate) {
}
