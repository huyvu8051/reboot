/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 4:34 PM
 */
package io.huyvu.reboot.backend.biz.user.board;

import io.huyvu.reboot.backend.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public long create(String name, long wpId, long userId) {
        Workspace wp = bdRepo.findWpById(wpId).orElseThrow();

        Board b = new Board()
                .setName(name)
                .setWorkspace(wp);
        Board bSaved = bdRepo.save(b);
        UserAccount user = bdRepo.findUser(userId).orElseThrow();

        BoardMemberKey bMemId = new BoardMemberKey()
                .setUser(user)
                .setBoard(bSaved);

        BoardMember bMem = new BoardMember()
                .setKey(bMemId);
        bdMemRepo.save(bMem);

        return b.getId();
    }
}
