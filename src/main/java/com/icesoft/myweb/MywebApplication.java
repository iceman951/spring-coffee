package com.icesoft.myweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MywebApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MywebApplication.class);
        app.run(args);
    }

}
