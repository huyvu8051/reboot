package io.huyvu.reboot.backend.biz.user.dashboard.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IServiceImpl implements IService {
    private final Repository repo;

    @Override
    public DashboardVo getBoard(long uId, Long wpId, Long bId, Long cId) {
        WpVo wp = null;
        List<WpVo> wps = null;
        BoardVo board = null;
        List<BoardVo> boards = null;
        List<WpMemberVo> wpMems = null;
        List<LiztVo> lizts = null;
        List<CardVo> cards = null;

        if (cId != null) {

        } else if (bId != null) {
            board = repo.selectBoard(bId, uId).orElseThrow();
            wp = repo.selectWp(board.wpId()).orElse(null);
            boards = repo.selectBoards(board.wpId());
            wpMems = repo.selectWpMem(board.wpId());
            lizts = repo.selectLizts(bId);

            cards = repo.selectCardsByBId(board
                    .id());

        } else if (wpId != null) {
            wp = repo.selectWp(wpId).orElseThrow();
            boards = repo.selectBoards(wpId);
            wpMems = repo.selectWpMem(wpId);
        }

        wps = repo.selectWps(uId);


        return new DashboardVo(wp, wps, board, boards, wpMems, lizts, cards);
    }
}
