package io.huyvu.reboot.backend.biz.user.dashboard.v1;

import io.huyvu.mybatix.Page;

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
        Page<AttachmentVo> attachments
) {
}
