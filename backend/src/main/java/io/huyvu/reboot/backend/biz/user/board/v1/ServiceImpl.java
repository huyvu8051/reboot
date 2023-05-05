/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 4:34 PM
 */
package io.huyvu.reboot.backend.biz.user.board.v1;

import io.socket.socketio.server.SocketIoNamespace;
import io.socket.socketio.server.SocketIoServer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.huyvu.reboot.backend.util.JsonUtils.toJsonObj;

@RequiredArgsConstructor
@Service
public class ServiceImpl implements IService {
    private final Repository bdRepo;

    private final String DASHBOARD = "/dashboard";
    private final SocketIoServer sioServer;

    @Override
    public List<BoardVo> getAll(long wpId) {
        return bdRepo.findAllOwnBoard(wpId);
    }

    @Override
    public long create(String name, long wpId, long uId) {
        bdRepo.findWpById(wpId, uId).orElseThrow();
        long bId = bdRepo.insertBoard(name, wpId);
        var board = bdRepo.findBoardById(bId);
        SocketIoNamespace nsp = sioServer.namespace(DASHBOARD);
        nsp.broadcast(String.valueOf(wpId), "update.dashboard.board", toJsonObj(board));
        return bId;
    }
}
