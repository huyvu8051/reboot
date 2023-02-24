/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 4:34 PM
 */
package io.huyvu.reboot.backend.biz.user.board.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BdServiceImpl implements BdService {
    private final BdRepository bdRepo;
    private final BdMemRepository bdMemRepo;


    @Override
    public List<BoardLsItem> getAll(long wpId, long userId) {
        return bdRepo.findAllOwnBoard(wpId, userId);
    }

    @Override
    public long create(String name, long wpId, long uId) {
        bdRepo.findWpById(wpId, uId).orElseThrow();
        return bdRepo.insertBoard(name, wpId);
    }
}
