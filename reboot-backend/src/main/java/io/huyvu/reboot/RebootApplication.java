package io.huyvu.reboot;

import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

import static org.springframework.util.ObjectUtils.isEmpty;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class RebootApplication {

    private final MinioClient minioClient;

    public static void main(String[] args) {

        new SpringApplicationBuilder(RebootApplication.class)
                .beanNameGenerator(new FullyQualifiedAnnotationBeanNameGenerator())
                .run(args);
    }


    @GetMapping("thread")
    String thread(){
        return Thread.currentThread().toString();
    }



    @SneakyThrows
    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("files") MultipartFile[] files) {
        if (files.length == 0) {
            return ResponseEntity.badRequest().body("Please upload a file");
        }


        var filenames = new ArrayList<>();

        for (MultipartFile file : files) {
            // Get the original filename
            String originalFilename = file.getOriginalFilename();
            filenames.add(originalFilename);
            PutObjectArgs poa = PutObjectArgs.builder()
                    .bucket("reboot-bucket")
                    .object(originalFilename)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build();

            // Upload the file to MinIO server
            minioClient.putObject(poa);
        }

        return ResponseEntity.ok("File uploaded successfully: " + filenames);
    }


    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> download(@PathVariable String filename) {
        if (isEmpty(filename)) {
            return ResponseEntity.badRequest().build();
        }

        GetObjectArgs goa = GetObjectArgs.builder()
                .bucket("reboot-bucket")
                .object(filename)
                .build();

        try (GetObjectResponse response = minioClient.getObject(goa)) {

            // Create InputStreamResource from the InputStream
            InputStreamResource resource = new InputStreamResource(response);

            // Build HttpHeaders with Content-Disposition attachment
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);

            // Set the Content-Type based on the filename extension
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

            // Return ResponseEntity with the resource, headers, and OK status
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
