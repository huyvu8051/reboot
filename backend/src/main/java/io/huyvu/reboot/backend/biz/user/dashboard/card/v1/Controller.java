package io.huyvu.reboot.backend.biz.user.dashboard.card.v1;

import io.huyvu.reboot.backend.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/dashboard/card")
public class Controller {
    private final IService service;
    private final String UPLOAD_DIR = "D://reboot/resources";

    @PostMapping
    void update(UpdateReq req) {
        service.update(req.cId(), req.title(), req.coverUrl(), req.memIds(), req.labelIds(), req.notification());
    }

    @ResponseBody
    @PostMapping("/attachment/{bId}")
    String uploadAttachment(@RequestParam("file") MultipartFile file, @PathVariable long bId) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        fileName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
        FileUploadUtil.saveFile(UPLOAD_DIR + "/" + bId, fileName, file);
        return fileName;
    }
}
