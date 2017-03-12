#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package de.fhdortmund.service.resource;

import de.fhdortmund.service.resource.entities.User;
import de.fhdortmund.service.resource.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.logging.Logger;

/**
 * Created by phil on 12.01.17.
 */
@SpringBootApplication
@RestController
@EnableResourceServer
@EnableEurekaClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceApplication {
    static Logger logger = Logger.getLogger(ResourceApplication.class.getName());

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public User home ( Principal principal) {
        return userService.getUserByUsername(principal.getName());
    }

    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication.class, args);
    }
}