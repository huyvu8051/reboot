package io.huyvu.reboot.backend.biz.user.dashboard.card.v1;

import io.huyvu.reboot.backend.biz.user.lizt.v1.UpdateCardDetailsReq;
import io.huyvu.reboot.backend.util.FileUploadUtil;
import io.socket.socketio.server.SocketIoNamespace;
import io.socket.socketio.server.SocketIoServer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

import static io.huyvu.reboot.backend.util.JsonUtils.toJsonObjExcludeNull;


@RequiredArgsConstructor
@Service
public class IServiceImpl implements IService {
    private final String DASHBOARD = "/dashboard";
    private final String UPLOAD_DIR = "D://reboot/resources";

    private final IRepository repository;
    private final SocketIoServer sioServer;

    @Override
    public List<BoardsDetailsResp> getBoardsDetails(long uId) {
        return repository.selectBoardsDetails(uId);
    }

    @Override
    public void updateCardDetails(UpdateCardDetailsReq req) {
        repository.updateCardDetails(req);
        long wpId = repository.selectWpId(req.id());
        SocketIoNamespace nsp = sioServer.namespace(DASHBOARD);
        nsp.broadcast(String.valueOf(wpId), "update.dashboard.card", toJsonObjExcludeNull(req));
    }

    @Override
    public String uploadAttachment(MultipartFile file, long cId) {
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        long bId = repository.selectBoardId(cId);
        String storeFileName = UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf("."));
        FileUploadUtil.saveFile(UPLOAD_DIR + "/" + bId, storeFileName, file);

        var fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        repository.insertAttachment(cId, originalFileName, storeFileName, fileExtension);
        return storeFileName;
    }
}
