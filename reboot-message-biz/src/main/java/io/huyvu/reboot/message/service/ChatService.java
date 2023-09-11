package io.huyvu.reboot.message.service;

import io.huyvu.reboot.message.model.SendMsgDTO;

public interface ChatService {
    void sendMsg(SendMsgDTO req);
}
