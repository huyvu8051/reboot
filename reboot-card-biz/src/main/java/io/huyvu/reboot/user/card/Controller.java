package io.huyvu.reboot.user.card;

import io.huyvu.reboot.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/dashboard/card")
public class Controller {
    private final IService service;

    @PostMapping("/attachment/{cId}")
    String uploadAttachment(@RequestParam("file") MultipartFile file, @PathVariable long cId) throws IOException {
        return service.uploadAttachment(file, cId);
    }

    @GetMapping("/boards-details")
    List<BoardsDetailsResp> getBoardsDetails() {
        return service.getBoardsDetails(SecurityUtils.uId());
    }

    @PutMapping("/details")
    void updateCardDetails(@RequestBody UpdateCardDetailsReq req) {
        service.updateCardDetails(req);
    }

    @PutMapping("/labeled")
    void updateCardLabeled(@RequestBody UpdateLabeledReq req) {
        service.updateCardLabeled(req);
    }

    @PutMapping("/label")
    void updateCardLabel(@RequestBody UpdateLabelReq req) {
        service.updateCardLabel(req);
    }

}