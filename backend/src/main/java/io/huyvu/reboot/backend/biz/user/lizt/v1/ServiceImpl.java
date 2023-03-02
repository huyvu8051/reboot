package io.huyvu.reboot.backend.biz.user.lizt.v1;

import io.socket.socketio.server.SocketIoNamespace;
import io.socket.socketio.server.SocketIoServer;
import lombok.AllArgsConstructor;
import lombok.Data;
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

            LiztVo mp;


            if (src.ordinal() < des.ordinal()) {
                mp = repo.selectLzNext(src.boardId(), des.ordinal(), true);
                if (mp == null) {
                    mp = new LiztVo(0, 0, 0, des.ordinal() + 100, "");
                }

                double num = mp.ordinal() - des.ordinal();
                double pow = Math.pow(10, Math.floor(Math.log10(num)));

                double firstDigit = num / pow;
                double rounded = Math.round(firstDigit) * pow;

                ordinal = des.ordinal() + (rounded / 2);

            } else {
                mp = repo.selectLzNext(src.boardId(), des.ordinal(), false);
                if (mp == null) {
                    mp = new LiztVo(0, 0, 0, des.ordinal() - 100, "");
                }

                double num = Math.abs(mp.ordinal() - des.ordinal());
                double pow = Math.pow(10, Math.floor(Math.log10(num)));

                double firstDigit = num / pow;
                double rounded = Math.round(firstDigit) * pow;

                ordinal = des.ordinal() - (rounded / 2);
            }


        }

        repo.updateLz(lId, title, ordinal, null);

        LiztVo lz = repo.selectLz(lId);

        SocketIoNamespace nsp = sioServer.namespace(DASHBOARD);
        nsp.broadcast(String.valueOf(lz.wId()), "update.dashboard", toJsonObj(lz));
    }

    @Override
    public void createCard(long lId, String title, long uId) {
        LiztVo liztVo = repo.selectLz(lId);
        repo.insertCard(lId, liztVo.boardId(), title);

        SocketIoNamespace nsp = sioServer.namespace(DASHBOARD);
        nsp.broadcast(String.valueOf(liztVo.wId()), "update.dashboard");
    }


}
