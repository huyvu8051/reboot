package io.huyvu.reboot.backend.biz.user.lizt.v1;

import io.socket.socketio.server.SocketIoNamespace;
import io.socket.socketio.server.SocketIoServer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static io.huyvu.reboot.backend.util.JsonUtils.toJsonObj;

@Service
@AllArgsConstructor
public class ServiceImpl implements IService {
    private final Repository repo;
    private final String DASHBOARD = "/dashboard";
    private final SocketIoServer sioServer;

    @Override
    public void createLz(String title, long bId, long uId) {
        long id = repo.insertLz(title, bId, uId);
        LiztVo lz = repo.selectLz(id);

        SocketIoNamespace nsp = sioServer.namespace(DASHBOARD);
        nsp.broadcast(String.valueOf(lz.wId()), "update.dashboard", toJsonObj(lz));
    }

    @Override
    public void updateLz(long lId, String title, Double ordinal, Long desId) {

        if (desId != null) {
            LiztVo src = repo.selectLz(lId);
            LiztVo des = repo.selectLz(desId);

            double d;
            if (src.ordinal() < des.ordinal())
                d = repo.selectLzNextOrdinal(desId);
            else
                d = repo.selectLzPrevOrdinal(desId);
            ordinal = getMiddleVal(d, des.ordinal());
        }

        repo.updateLz(lId, title, ordinal, null);

        LiztVo lz = repo.selectLz(lId);

        SocketIoNamespace nsp = sioServer.namespace(DASHBOARD);
        nsp.broadcast(String.valueOf(lz.wId()), "update.dashboard.list", toJsonObj(lz));
    }

    @Override
    public void createCard(long lId, String title, long uId) {
        LiztVo liztVo = repo.selectLz(lId);
        repo.insertCard(lId, liztVo.boardId(), title);

        SocketIoNamespace nsp = sioServer.namespace(DASHBOARD);
        nsp.broadcast(String.valueOf(liztVo.wId()), "update.dashboard");
    }

    @Override
    public void updateCard(long cId, double ordinal, Long lId) {
        repo.updateCardOrdinal(cId, ordinal, lId);
        long wpId = repo.selectWpId(cId);

        SocketIoNamespace nsp = sioServer.namespace(DASHBOARD);
        nsp.broadcast(String.valueOf(wpId), "update.dashboard");


    }

    double getMiddleVal(double v1, double v2) {
        double diff = Math.abs(v1 - v2);
        double pow = Math.pow(10, Math.floor(Math.log10(diff)));

        double firstDigit = diff / pow;
        double rounded = Math.round(firstDigit) * pow;

        if (v2 > v1) {
            return v2 - (rounded / 2);
        }

        return v1 - (rounded / 2);

    }


}
