/**
 * @Author HuyVu
 * @CreatedDate 3/1/2023 9:41 AM
 */
package io.huyvu.reboot.message.socket;

import io.socket.socketio.server.SocketIoNamespace;
import io.socket.socketio.server.SocketIoServer;
import io.socket.socketio.server.SocketIoSocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SocketCfg {

    @Bean
    ApplicationRunner configSocketConnection(SocketIoServer sioServer) {
        return args -> {
            SocketIoNamespace nsp = sioServer.namespace("/message");

            nsp.on("connection", args2 -> {
                SocketIoSocket socket = (SocketIoSocket) args2[0];
                String id = socket.getId();
                log.info("client {} connected", id);
            });
        };
    }
}
