package com.sparta.hanghaechabak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HanghaechabakApplication {

//    public static final String APPLICATION_LOCATIONS = "spring.config.location="
//            + "classpath:application.yml,"
//            + "classpath:aws.yml";
//
//    public static void main(String[] args) {
//        new SpringApplicationBuilder(HanghaechabakApplication.class)
//                .properties(APPLICATION_LOCATIONS)
//                .run(args);
//    }

    public static void main(String[] args) {
        SpringApplication.run(HanghaechabakApplication.class, args);

    }

}
