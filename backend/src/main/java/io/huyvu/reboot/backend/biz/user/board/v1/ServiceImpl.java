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
public class ServiceImpl implements IService {
    private final Repository bdRepo;

    @Override
    public List<BoardVo> getAll(long wpId) {
        return bdRepo.findAllOwnBoard(wpId);
    }

    @Override
    public long create(String name, long wpId, long uId) {
        bdRepo.findWpById(wpId, uId).orElseThrow();
        long bId = bdRepo.insertBoard(name, wpId);
        return bId;
    }
}
