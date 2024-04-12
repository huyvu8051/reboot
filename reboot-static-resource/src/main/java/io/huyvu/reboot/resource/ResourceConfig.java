package io.huyvu.reboot.resource;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ResourceConfig {

    @Value("${bucket.name}")
    private static String BUCKET_NAME = "reboot-bucket";
    @Value("${bucket.endpoint}")
    private static String BUCKET_ENDPOINT = "http://localhost:9000";

    @SneakyThrows
    @Bean
    MinioClient minioClient() {
        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint(BUCKET_ENDPOINT)
                        .credentials("q0jPh0cbQqPzVSSyLpSZ", "UwkkgRqKhF5iZQeCtBGGptvphKn9WWMe8eDkYmI2")
                        .build();


        // Make 'asiatrip' bucket if not exist.
        boolean found =
                minioClient.bucketExists(BucketExistsArgs.builder().bucket(BUCKET_NAME).build());
        if (!found) {
            // Make a new bucket called 'asiatrip'.
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(BUCKET_NAME).build());
        } else {
            log.info("Bucket '{}' already exists.", BUCKET_NAME);
        }
        return minioClient;
    }
}
