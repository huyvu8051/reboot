package io.huyvu.reboot.user.lizt;

public record UpdateCardDetailsReq(long id,Long lId, String title, Double ordinal, String coverUrl, String description, Boolean isDeleted) {
}
