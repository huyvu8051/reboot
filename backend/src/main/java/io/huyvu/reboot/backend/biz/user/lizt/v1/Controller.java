package io.huyvu.reboot.backend.biz.user.lizt.v1;

import io.huyvu.reboot.backend.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user/list")
@PreAuthorize("isAuthenticated()")
public class Controller {
    private final IService service;

    @PostMapping
    void createLz(String title, long bId){
        long id = SecurityUtils.currentContext().id();
        service.createLz(title, bId, id);
    }


}
