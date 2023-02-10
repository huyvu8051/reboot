package io.huyvu.reboot.backend.controller;

import com.github.javafaker.Faker;
import io.huyvu.reboot.backend.dto.Student;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class HomeController {

    Faker faker = new Faker(new Locale("vi-VN"));

    @GetMapping(value = "/data")
    public ResponseEntity<StreamingResponseBody> streamData() {
        StreamingResponseBody responseBody = response -> {
            for (int i = 1; i <= 1000; i++) {
                try {
                    Thread.sleep(10);
                    response.write(("Data stream line - " + i + "\n").getBytes());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(responseBody);
    }

    @GetMapping(value = "/flux", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    Flux<Student> home() {
        return Flux.interval(Duration.ofMillis(200)).map(i -> new Student(faker.idNumber().valid(), faker.name().fullName(), faker.university().name().toString(), faker.number().numberBetween(16, 99)));
    }

    @GetMapping(value = "/srb")
    ResponseEntity<StreamingResponseBody> srb() {
        StreamingResponseBody stream = out -> {
            for (int i = 0; i < 1000; i++) {
                String msg = "/srb" + " @ " + new Date();
                out.write(msg.getBytes());

            }
        };
        return ResponseEntity.ok(stream);
    }


    @GetMapping(value = "/ls", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    List<Student> list() {
        List<Student> rs = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            rs.add(new Student(faker.idNumber().valid(), faker.name().fullName(), faker.university().name().toString(), faker.number().numberBetween(16, 99)));
        }

        return rs;
    }


    @GetMapping("/public")
    String pub() {
        return "public";
    }

    @GetMapping("/private")
    String pri() {
        return "private";
    }
}