package com.example.fa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing    //CreatedDate 사용하기 위해서 추가해줘야 함
public class FaApplication {

    public static void main(String[] args) {
        SpringApplication.run(FaApplication.class, args);
    }

}
