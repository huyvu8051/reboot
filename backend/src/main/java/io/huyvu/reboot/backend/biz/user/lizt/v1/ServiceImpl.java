package io.huyvu.reboot.backend.biz.user.lizt.v1;

import io.socket.socketio.server.SocketIoNamespace;
import io.socket.socketio.server.SocketIoServer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServiceImpl implements IService {
    private final Repository repo;
    private final String DASHBOARD = "/dashboard";
    private final SocketIoServer sioServer;
    @Override
    public void createLz(String title,long bId, long uId) {
        long id = repo.insertLz(title, bId, uId);
        LiztVo lz = repo.selectLz(id);

        SocketIoNamespace nsp = sioServer.namespace(DASHBOARD);
        nsp.broadcast(String.valueOf(lz.wpId()), "update.lz", lz);

    }
}
