/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 4:34 PM
 */
package io.huyvu.reboot.biz.board.service;

import io.huyvu.reboot.biz.board.model.BoardVo;
import io.huyvu.reboot.biz.board.repository.Repository;
import io.huyvu.reboot.socket.util.JsonUtils;
import io.socket.socketio.server.SocketIoNamespace;
import io.socket.socketio.server.SocketIoServer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        nsp.broadcast(String.valueOf(wpId), "update.dashboard.board", JsonUtils.toJsonObj(board));
        return bId;
    }
}
