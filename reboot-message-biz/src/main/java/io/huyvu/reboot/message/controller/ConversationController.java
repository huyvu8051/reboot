package io.huyvu.reboot.message.controller;

import io.huyvu.reboot.message.model.MessageInitCondDTO;
import io.huyvu.reboot.message.model.MessageInitDTO;
import io.huyvu.reboot.message.model.SaveConvDTO;
import io.huyvu.reboot.message.model.SendMsgDTO;
import io.huyvu.reboot.message.service.ChatService;
import io.huyvu.reboot.message.service.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/message")
public class ConversationController {
    private final ConversationService conversationService;
    private final ChatService chatService;

    @PostMapping("conv/save")
    void save(@RequestBody SaveConvDTO conv){
        conversationService.save(conv);
    }

    @PostMapping("conv/list")
    io.huyvu.reboot.message.model.ConvListDTO list(){
        var resp = io.huyvu.reboot.message.model.ConvListDTO.builder()
                .convs(conversationService.getList())
                .build();
        return resp;
    }
    @PostMapping("init")
    MessageInitDTO init(@RequestBody MessageInitCondDTO cond){
        var resp = MessageInitDTO.builder()
                .convs(conversationService.getList())
                .msgs(conversationService.getMessages(cond))
                .build();
        return resp;
    }

    @PostMapping("chat/send")
    void sendMsg(@RequestBody SendMsgDTO req){
        chatService.sendMsg(req);
    }
}
