package io.huyvu.reboot.backend.biz.user.dashboard.v1;

import io.huyvu.mybatix.Page;
import io.huyvu.mybatix.Paging;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IServiceImpl implements IService {
    private final Repository repo;

    @Override
    public DashboardVo getBoard(long uId, Long wpId, Long bId, Long cId) {
        WpVo wp = null;
        List<WpVo> wps;
        BoardVo board = null;
        List<BoardVo> boards = new ArrayList<>(0);
        List<WpMemberVo> wpMems = new ArrayList<>(0);
        List<LiztVo> lizts = new ArrayList<>(0);
        List<CardItemVo> cards = new ArrayList<>(0);
        CardDetailsVo card = null;
        Page<AttachmentVo> attachments = new Page<>() {
            @Override
            public int getTotalCount() {
                return 0;
            }

            @Override
            public List<AttachmentVo> getItems() {
                return new ArrayList<>();
            }
        };

        List<CardMember> cardMembers = new ArrayList<>();
        List<CardLabel> cardLabels = new ArrayList<>();
        List<BoardLabel> boardLabels = new ArrayList<>();


        if (cId != null) {
            card = repo.selectCardDetails(cId, uId);
            cardMembers = repo.selectCardMems(cId);
            cardLabels = repo.selectCardLabels(cId);

            attachments = repo.selectAttachments(cId, Paging.of(0, 5));
            bId = card.getBId();

        }

        if (bId != null) {
            board = repo.selectBoard(bId).orElseThrow();
            lizts = repo.selectLizts(bId);
            cards = repo.selectCardsByBId(board
                    .id());
            wpId = board.wpId();
            boardLabels = repo.selectBoardLabels(bId);
        }

        if (wpId != null) {
            wp = repo.selectWp(wpId).orElseThrow();
            boards = repo.selectBoards(wpId);
            wpMems = repo.selectWpMem(wpId);
        }

        wps = repo.selectWps(uId);


        return new DashboardVo(wp, wps, board, boards, wpMems, lizts, cards, card, cardMembers, cardLabels, boardLabels, attachments);
    }
}
