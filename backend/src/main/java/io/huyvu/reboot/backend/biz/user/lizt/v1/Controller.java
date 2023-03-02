package io.huyvu.reboot.backend.biz.user.lizt.v1;

import io.huyvu.reboot.backend.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
@PreAuthorize("isAuthenticated()")
public class Controller {
    private final IService service;

    @PostMapping("list")
    void createLz(String title, long bId){
        long id = SecurityUtils.currentContext().id();
        service.createLz(title, bId, id);
    }


    @PutMapping("list")
    void updateLz(long lId, String title, Double ordinal, Long desId){
        long id = SecurityUtils.currentContext().id();
        service.updateLz(lId, title, ordinal, desId);
    }


    @PostMapping("card")
    void createCard(long lId, String title){
        long uId = SecurityUtils.currentContext().id();
        service.createCard(lId, title, uId);
    }




}
