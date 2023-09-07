package io.huyvu.reboot.user.dashboard;


import java.util.List;

public record DashboardVo(
        WpVo wp,
        List<WpVo> wps,
        BoardVo board,
        List<BoardVo> boards,
        List<WpMemberVo> wpMems,
        List<LiztVo> lizts,
        List<CardItemVo> cards,
        CardDetailsVo card,
        List<CardMember> cardMems,
        List<CardLabel> cardLabels,
        List<BoardLabel> boardLabels,
        List<AttachmentVo> attachments
) {
}
