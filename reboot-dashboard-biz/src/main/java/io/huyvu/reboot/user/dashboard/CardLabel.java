package io.huyvu.reboot.user.dashboard;

public record CardLabel(
        long id,
        long labelId,
        long cardId,
        boolean isDeleted
) {
}
