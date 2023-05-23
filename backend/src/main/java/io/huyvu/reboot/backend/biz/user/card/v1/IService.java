package io.huyvu.reboot.backend.biz.user.card.v1;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IService {

    List<BoardsDetailsResp> getBoardsDetails(long uId);
    void updateCardDetails(UpdateCardDetailsReq req);

    String uploadAttachment(MultipartFile file, long cId);

    void updateCardLabeled(UpdateLabeledReq req);
}
