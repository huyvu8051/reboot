package io.huyvu.reboot.backend.biz.user.dashboard.card.v1;

import io.huyvu.reboot.backend.biz.user.lizt.v1.UpdateCardDetailsReq;
import io.socket.socketio.server.SocketIoNamespace;
import io.socket.socketio.server.SocketIoServer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.huyvu.reboot.backend.util.JsonUtils.toJsonObjExcludeNull;


@RequiredArgsConstructor
@Service
public class IServiceImpl implements IService {
    private final String DASHBOARD = "/dashboard";

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
}
