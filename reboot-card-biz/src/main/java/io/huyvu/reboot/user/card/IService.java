package io.huyvu.reboot.user.card;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IService {

    List<BoardsDetailsResp> getBoardsDetails(long uId);
    void updateCardDetails(UpdateCardDetailsReq req);

    String uploadAttachment(MultipartFile file, long cId);

    void updateCardLabeled(UpdateLabeledReq req);

    void updateCardLabel(UpdateLabelReq req);
}
