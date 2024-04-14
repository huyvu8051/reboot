package io.huyvu.reboot.message.service;

import io.huyvu.reboot.message.mapper.ChatMapper;
import io.huyvu.reboot.message.model.SendMsgDTO;
import io.huyvu.reboot.security.util.SecurityUtils;
import io.huyvu.reboot.socket.util.JsonUtils;
import io.socket.socketio.server.SocketIoNamespace;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static io.huyvu.reboot.socket.service.ChatBroadcastProvider.CHAT_NAMESPACE;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatMapper chatMapper;
    @Qualifier(CHAT_NAMESPACE)
    private final SocketIoNamespace chatNamespace;

    @Override
    public void sendMsg(SendMsgDTO req) {
        var uId = SecurityUtils.uId();
        req.setUId(uId);
        chatMapper.insertMsg(req, uId);
        req.setId(req.getSendAt());
        chatNamespace.broadcast(req.getCId(), "sendMsg", JsonUtils.toJsonObj(req));
    }
}
