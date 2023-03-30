package io.huyvu.reboot.backend.biz.user.dashboard.card.v1;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IServiceImpl implements IService {
    @Override
    public void update(long cId, String title, String coverUrl, List<Long> memIds, List<Long> labelIds, Boolean notification) {

    }
}
