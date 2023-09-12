package io.huyvu.reboot.backend.config;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Random;


@Component
@AllArgsConstructor
@Slf4j
public class DataLoader implements ApplicationRunner {

//    private final PasswordEncoder passwordEncoder;
//    private final Repository repo;


    public void run(ApplicationArguments args) {
        /*var attachmentVoPage = repo.selectAttachments(15, Paging.of(1, 5));

        System.out.println(attachmentVoPage);
*/

        var random = new Random();

        var faker = new Faker(new Locale("vi-VN"));

//        IntStream.range(6457885, 9999999).parallel().forEach(i->ggRepo.save("userNm" + i,"chung ta", "chung ta"))

        // start time
        var t0 = System.nanoTime();

        // end time
        var t1 = System.nanoTime();


    }

}