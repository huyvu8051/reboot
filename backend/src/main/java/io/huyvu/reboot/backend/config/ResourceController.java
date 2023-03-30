package io.huyvu.reboot.backend.config;

import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ResourceController {
    private final String UPLOAD_DIR = "D://reboot/resources";
 
    @GetMapping("/resources/board/{bId}/{fileNm}")
    public ResponseEntity<byte[]> getResource(@PathVariable long bId, @PathVariable String fileNm) throws IOException {
        
        // Read the resource file from disk
        Path resourcePath = Paths.get(UPLOAD_DIR + "/" + bId + "/", fileNm);
        byte[] resourceBytes = Files.readAllBytes(resourcePath);
        
        // Set the response headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength(resourceBytes.length);
        headers.setContentDisposition(ContentDisposition.attachment().filename(fileNm).build());
        
        // Return the resource as a byte array
        return new ResponseEntity<>(resourceBytes, headers, HttpStatus.OK);
    }
}
