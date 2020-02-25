package com.spring.boot.app;

import com.spring.boot.config.AppConfiguration;
import com.spring.boot.controller.WebhooksController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebhooksApplication {
    public static void main(final String[] args) {
        SpringApplication.run(new Class[]{WebhooksApplication.class, AppConfiguration.class, WebhooksController.class}, args);
    }
}
