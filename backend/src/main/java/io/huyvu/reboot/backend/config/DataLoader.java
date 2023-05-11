package io.huyvu.reboot.backend.config;

import com.github.javafaker.Faker;
import io.huyvu.reboot.backend.biz.user.dashboard.v1.AttachmentVo;
import io.huyvu.reboot.backend.biz.user.dashboard.v1.Repository;
import io.huyvu.reboot.backend.config.mybatis.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Random;


@Component
@AllArgsConstructor
@Slf4j
public class DataLoader implements ApplicationRunner {

    private final PasswordEncoder passwordEncoder;
    private final Repository repo;


    public void run(ApplicationArguments args) {
        var attachmentVoPage = repo.selectAttachments(15);


        Random random = new Random();

        Faker faker = new Faker(new Locale("vi-VN"));

//        IntStream.range(6457885, 9999999).parallel().forEach(i->ggRepo.save("userNm" + i,"chung ta", "chung ta"))

        // start time
        long t0 = System.nanoTime();

        // end time
        long t1 = System.nanoTime();


    }

}