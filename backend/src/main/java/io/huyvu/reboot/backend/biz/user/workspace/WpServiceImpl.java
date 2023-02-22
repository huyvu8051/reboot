/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 11:26 AM
 */
package io.huyvu.reboot.backend.biz.user.workspace;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WpServiceImpl implements WpService {
    private final WpRepository wpRepo;

    @Transactional
    @Override
    public WpDetails create(String wpNm, long adminId) {
        long wpId = wpRepo.insertWp(wpNm);
        WpMem wpMem = wpRepo.insertWpMem(wpId, adminId, true);
        return wpRepo.findWpDetails(wpId, adminId);
    }

    @Override
    public List<ListWpItem> getList(long userId) {
        return wpRepo.findAllWpItem(userId);
    }

    @Override
    public WpDetails getDetails(long wpId, long id) {

        return wpRepo.findWpDetails(wpId, id);
    }
}
