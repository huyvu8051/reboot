/**
 * @Author HuyVu
 * @CreatedDate 5/12/2023 10:48 AM
 */
package io.huyvu.reboot.backend.config.socketio;

import io.socket.socketio.server.SocketIoNamespace;
import io.socket.socketio.server.SocketIoServer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardBroadcastImpl implements DashboardBroadcast {
    private static final String DASHBOARD = "/dashboard";
    private final Repository repo;
    private final SocketIoServer sio;

    @Override
    public void byCardId(long cId, String event, Object... toJsonObj) {
        long wId = repo.selectWpIdFromCId(cId);
        byWorkspaceId(wId, event, toJsonObj);
    }

    @Override
    public void byBoardId(long bId, String event, Object... toJsonObj) {
        long wId = repo.selectWpIdFromBId(bId);
        byWorkspaceId(wId, event, toJsonObj);
    }


    @Override
    public void byWorkspaceId(long wId, String event, Object... toJsonObj) {
        SocketIoNamespace nsp = sio.namespace(DASHBOARD);
        nsp.broadcast(String.valueOf(wId), event, toJsonObj);
    }


}
