package io.huyvu.reboot.backend.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
public class FileUploadUtil {

    public static void saveFile(String dir, String fileNm, MultipartFile mf) throws IOException {
        Path uploadPath = Paths.get(dir + "/" + fileNm);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = mf.getInputStream()) {

            Files.copy(inputStream, uploadPath, StandardCopyOption.REPLACE_EXISTING);
            log.info(uploadPath.toString());
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileNm, ioe);
        }
    }

}