package io.huyvu.reboot.message.controller;

import io.huyvu.reboot.message.model.SaveConvDTO;
import io.huyvu.reboot.message.service.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/message/conv")
public class ConversationController {
    private final ConversationService service;

    @PostMapping("save")
    void save(@RequestBody SaveConvDTO conv){
        service.save(conv);
    }

    @PostMapping("list")
    io.huyvu.reboot.message.controller.ConvListDTO list(){
       return io.huyvu.reboot.message.controller.ConvListDTO.builder()
               .convs(service.getList())
               .build();
    }

}
