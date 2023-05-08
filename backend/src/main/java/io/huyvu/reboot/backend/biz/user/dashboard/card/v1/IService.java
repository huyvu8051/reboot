package io.huyvu.reboot.backend.biz.user.dashboard.card.v1;

import io.huyvu.reboot.backend.biz.user.lizt.v1.UpdateCardDetailsReq;

import java.util.List;

public interface IService {

    List<BoardsDetailsResp> getBoardsDetails(long uId);
    void updateCardDetails(UpdateCardDetailsReq req);
}
