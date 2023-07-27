package io.huyvu.reboot.user.card;

import io.huyvu.reboot.resource.FileUploadUtil;
import io.huyvu.reboot.socket.service.DashboardBroadcast;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

import static io.huyvu.reboot.socket.util.JsonUtils.toJsonObj;
import static io.huyvu.reboot.socket.util.JsonUtils.toJsonObjExcludeNull;


@RequiredArgsConstructor
@Service
public class IServiceImpl implements IService {

    @Value("${reboot.upload.dir}")
    private String UPLOAD_DIR;

    private final IRepository repository;

    private final DashboardBroadcast dashboardBroadcast;

    @Override
    public List<BoardsDetailsResp> getBoardsDetails(long uId) {
        return repository.selectBoardsDetails(uId);
    }

    @Override
    public void updateCardDetails(UpdateCardDetailsReq req) {
        repository.updateCardDetails(req);
        dashboardBroadcast.byCardId(req.id(), "update.dashboard.card", toJsonObjExcludeNull(req));
    }

    @Override
    public String uploadAttachment(MultipartFile file, long cId) {
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        var extensionIndex = originalFileName.lastIndexOf(".");
        String storeFileName = UUID.randomUUID() + originalFileName.substring(extensionIndex);

        long bId = repository.selectBoardId(cId);
        FileUploadUtil.saveFile(UPLOAD_DIR + "/" + bId, storeFileName, file);

        var fileExtension = originalFileName.substring(extensionIndex + 1);
        var attachmentId = repository.insertAttachment(cId, originalFileName, storeFileName, fileExtension);

        AttachmentVo attachment =  repository.selectAttachment(attachmentId);
        dashboardBroadcast.byCardId(cId, "update.dashboard.attachment", toJsonObj(attachment));
        return storeFileName;
    }

    @Override
    public void updateCardLabeled(UpdateLabeledReq req) {
        if(req.getId() == null){
            long id = repository.insertLabeled(req.getLabelId(), req.getCardId());
            req.setId(id);
        }else {
            repository.updateLabeled(req.getId(), req.isDeleted());
        }
        dashboardBroadcast.byCardId(req.getCardId(), "update.dashboard.labeled", toJsonObj(req));
    }

    @Override
    public void updateCardLabel(UpdateLabelReq req) {
        if(req.getId() == null){
            long id = repository.insertLabel(req.getBId(),req.getTitle(), req.getColor());
            req.setId(id);
        }else {
            repository.updateLabel(req);
        }
        dashboardBroadcast.byBoardId(req.getBId(), "update.dashboard.label", toJsonObj(req));

    }


}