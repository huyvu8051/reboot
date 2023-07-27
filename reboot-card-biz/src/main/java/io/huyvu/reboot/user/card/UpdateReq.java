package io.huyvu.reboot.user.card;

import java.util.List;

public record UpdateReq(
        long cId,
        String title,
        String coverUrl,
        List<Long> memIds,
        List<Long> labelIds,
        Boolean notification
) {
}
