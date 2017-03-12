package de.fhdortmund.service.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by phil on 12.02.17.
 */
@SpringBootApplication
@Controller
@EnableResourceServer
@EnableEurekaClient
public class DemoApplication {

    @RequestMapping("/")
    public String home ( Principal principal) {
        String greeting = "Hello ";
        return greeting + principal.getName();
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
