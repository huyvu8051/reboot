package io.huyvu.reboot.backend.biz.user.dashboard.v1;

public record CardLabel(
        long id,
        long labelId,
        long cardId,
        boolean isDeleted
) {
}
