package io.huyvu.reboot.backend.biz.user.card.v1;

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
