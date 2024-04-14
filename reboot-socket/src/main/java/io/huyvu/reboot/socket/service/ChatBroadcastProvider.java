package io.huyvu.reboot.socket.service;

import io.huyvu.reboot.security.util.SecurityUtils;
import io.huyvu.reboot.socket.mapper.SocketManagementMapper;
import io.socket.socketio.server.SocketIoNamespace;
import io.socket.socketio.server.SocketIoServer;
import io.socket.socketio.server.SocketIoSocket;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@AllArgsConstructor
public class ChatBroadcastProvider {
    private final SocketIoServer sio;
    private final SocketManagementMapper smMapper;
    public static final String CHAT_NAMESPACE = "chat";

    @Bean(CHAT_NAMESPACE)
    SocketIoNamespace chatNamespace() {
        var namespace = sio.namespace(CHAT_NAMESPACE);
        namespace.on("connection", args -> {
            SocketIoSocket socket = (SocketIoSocket) args[0];
            var uId = SecurityUtils.uId();
            var roomIds = smMapper.selectAllJoinedRooms(uId);
            socket.joinRoom(roomIds.toArray(new String[0]));
            log.info("Client {} ({}) has connected.", socket.getId(), uId);
        });
        return namespace;
    }
}
