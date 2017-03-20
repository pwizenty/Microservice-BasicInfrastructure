package de.fhdortmund.service.resource.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import de.fhdortmund.service.resource.entities.User;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * Created by phil on 01.02.17.
 */
@Service
public class UserService {
    @LoadBalanced
    private  RestTemplate restTemplate;

    public UserService(RestTemplate template) {
        this.restTemplate = template;
    }

    /**
     * This Methodes consumes a rest endpoint from the usermanagement service
     *
     * @param username username of the logged in user.
     * @return full user information
     */
    @HystrixCommand(fallbackMethod = "reliable")
    public User getUserByUsername(String username) {
        URI uri = URI.create("http://localhost:9090/get-by-username?username=" + username );
        System.out.println("RESTFALL");
        User user = restTemplate.getForObject(uri, User.class);
        if( user == null) {
            System.out.println("User is null");
        } else {
            System.out.println("Username:" + user.getUsername());
        }

        return this.restTemplate.getForObject(uri, User.class);
    }


    public User reliable(String username) {
        return new User("Email", "Surname","Firstname", "username", "password");
    }
}
