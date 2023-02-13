/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 4:34 PM
 */
package io.huyvu.reboot.backend.user.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BdServiceImpl implements BdService {
    private final BdRepository bdRepo;


    @Override
    public List<BoardLsItem> getAll(long id) {
        return bdRepo.findAllOwnBoard(id);
    }

    @Override
    public long create(String name, long id) {
        return 0;
    }
}
