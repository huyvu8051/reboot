package io.huyvu.reboot.message.controller;

import io.huyvu.reboot.message.model.MessageInitCondDTO;
import io.huyvu.reboot.message.model.MessageInitDTO;
import io.huyvu.reboot.message.model.SaveConvDTO;
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
    private final ConversationService service;

    @PostMapping("conv/save")
    void save(@RequestBody SaveConvDTO conv){
        service.save(conv);
    }

    @PostMapping("conv/list")
    io.huyvu.reboot.message.controller.ConvListDTO list(){
        var resp = io.huyvu.reboot.message.controller.ConvListDTO.builder()
                .convs(service.getList())
                .build();
        return resp;
    }
    @PostMapping("init")
    MessageInitDTO init(MessageInitCondDTO cond){
        var resp = MessageInitDTO.builder()
                .convs(service.getList())
                .msgs(service.getMessages(cond))
                .build();
        return resp;
    }
}
