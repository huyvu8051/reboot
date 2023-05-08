package io.huyvu.reboot.backend.biz.user.dashboard.card.v1;

import io.huyvu.reboot.backend.biz.user.lizt.v1.UpdateCardDetailsReq;
import io.huyvu.reboot.backend.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static io.huyvu.reboot.backend.util.SecurityUtils.uId;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/dashboard/card")
public class Controller {
    private final IService service;
    private final String UPLOAD_DIR = "D://reboot/resources";

    @PostMapping("/attachment/{bId}")
    String uploadAttachment(@RequestParam("file") MultipartFile file, @PathVariable long bId) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        fileName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
        FileUploadUtil.saveFile(UPLOAD_DIR + "/" + bId, fileName, file);
        return fileName;
    }

    @GetMapping("/boards-details")
    List<BoardsDetailsResp> getBoardsDetails() {
        return service.getBoardsDetails(uId());
    }
    @PutMapping("/details")
    void updateCardDetails(@RequestBody UpdateCardDetailsReq req) {
        service.updateCardDetails(req);
    }
}
