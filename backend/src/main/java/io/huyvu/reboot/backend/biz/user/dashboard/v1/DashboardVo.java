package io.huyvu.reboot.backend.biz.user.dashboard.v1;


import java.util.List;

public record DashboardVo(
        WpVo wp,
        List<WpVo> wps,
        BoardVo board,
        List<BoardVo> boards,
        List<WpMemberVo> wpMems,
        List<LiztVo> lizts,
        List<CardVo> cards,
        CardDetailsVo card
) {
}
