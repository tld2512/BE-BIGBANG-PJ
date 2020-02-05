package com.pj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
//@EntityScan("com.pj.models.*")
public class PjApplication {

    public static void main(String[] args) {
        SpringApplication.run(PjApplication.class, args);
    }

}
