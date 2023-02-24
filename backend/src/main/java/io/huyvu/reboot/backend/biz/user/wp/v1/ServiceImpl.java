/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 11:26 AM
 */
package io.huyvu.reboot.backend.biz.user.wp.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceImpl implements Service {
    private final Repository wpRepo;

    @Transactional
    @Override
    public WpDetails create(String wpNm, long adminId) {
        long l = wpRepo.insertWp(wpNm);
        long wpId = wpRepo.selectLastInsertId();
        wpRepo.insertWpMem(wpId, adminId, true);
        return wpRepo.selectWpDetails(wpId, adminId).orElseThrow();
    }

    @Override
    public List<ListWpItem> getList(long userId) {
        return wpRepo.selectWpItem(userId);
    }

    @Override
    public WpDetails getDetails(long wpId, long id) {
        return wpRepo.selectWpDetails(wpId, id).orElseThrow();
    }
}
