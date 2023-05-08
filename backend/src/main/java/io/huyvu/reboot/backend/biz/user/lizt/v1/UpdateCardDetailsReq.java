package io.huyvu.reboot.backend.biz.user.lizt.v1;

public record UpdateCardDetailsReq(long id,Long lId, String title, Double ordinal, String coverUrl, String description, Boolean isDeleted) {
}
