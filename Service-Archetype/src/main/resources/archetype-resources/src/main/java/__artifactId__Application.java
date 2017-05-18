#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.security.Principal;

/**
 * Created by phil on 12.02.17.
 *
 * This class provides a simple rest endpoint.
 */
@SpringBootApplication
@EnableResourceServer
@EnableEurekaClient
public class ${artifactId}Application {
    public static void main(String[] args) {
        SpringApplication.run(${artifactId}Application.class, args);
    }
}
