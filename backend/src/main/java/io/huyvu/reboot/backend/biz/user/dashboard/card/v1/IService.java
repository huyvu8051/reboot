package io.huyvu.reboot.backend.biz.user.dashboard.card.v1;

import java.util.List;

public interface IService {
    void update(long cId, String title, String coverUrl, List<Long> memIds, List<Long> labelIds, Boolean notification);
}
