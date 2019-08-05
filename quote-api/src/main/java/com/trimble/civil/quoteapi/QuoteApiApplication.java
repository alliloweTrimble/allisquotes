package com.trimble.civil.quoteapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@Configuration
@EnableWebMvc
public class QuoteApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuoteApiApplication.class, args);
    }

}
