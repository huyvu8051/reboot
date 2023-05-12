/**
 * @Author HuyVu
 * @CreatedDate 5/12/2023 10:48 AM
 */
package io.huyvu.reboot.backend.config.socketio;

import io.socket.socketio.server.SocketIoNamespace;
import io.socket.socketio.server.SocketIoServer;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import static io.huyvu.reboot.backend.util.JsonUtils.toJsonObjExcludeNull;

@Service
@RequiredArgsConstructor
public class DashboardBroadcastImpl implements DashboardBroadcast {
    private static final String DASHBOARD = "/dashboard";
    private final Repository repo;
    private final SocketIoServer sio;
    @Override
    public void byCardId(long cId, String event, Object... toJsonObj) {
        long wId = repo.selectWpIdFromCId(cId);
        SocketIoNamespace nsp = sio.namespace(DASHBOARD);
        nsp.broadcast(String.valueOf(wId), event, toJsonObj);
    }
}
