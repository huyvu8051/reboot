package io.huyvu.reboot.backend.controller;

import com.github.javafaker.Faker;
import io.huyvu.reboot.backend.dto.Student;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
public class HomeController {

    Faker faker = new Faker(new Locale("vi-VN"));

    @GetMapping(value = "/home", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    Flux<Student> home(){
         return Flux.interval(Duration.ofMillis(101)).map(i -> new Student(faker.idNumber().valid(), faker.name().fullName(), faker.university().name().toString(), faker.number().numberBetween(16, 99)));
    }
    @GetMapping(value = "/ls", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    List<Student> list(){
        List<Student> rs = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            rs.add(new Student(faker.idNumber().valid(), faker.name().fullName(), faker.university().name().toString(), faker.number().numberBetween(16, 99)));
        }

         return rs;
    }
}
