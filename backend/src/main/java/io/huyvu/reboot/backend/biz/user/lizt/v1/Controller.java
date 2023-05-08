package io.huyvu.reboot.backend.biz.user.lizt.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static io.huyvu.reboot.backend.util.SecurityUtils.uId;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class Controller {
    private final IService service;

    @PostMapping("list")
    void createLz(String title, long bId) {
        service.createLz(title, bId, uId());
    }


    @PutMapping("list")
    void updateLz(long lId, String title, Double ordinal) {
        service.updateLz(lId, title, ordinal);
    }


    @PostMapping("card")
    void createCard(long lId, String title) {
        service.createCard(lId, title, uId());
    }

    @PutMapping("card")
    void updateCard(long cId, double ordinal, Long lId) {
        service.updateCard(cId, ordinal, lId);
    }




}
