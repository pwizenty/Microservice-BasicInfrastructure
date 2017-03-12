package de.fhdortmund.service.resource.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

/**
 * Created by phil on 01.02.17.
 */
@Configuration
public class RestConfig {
        @Bean
        public OAuth2RestTemplate restTemplate(UserInfoRestTemplateFactory userInfoRestTemplateFactory) {
            return userInfoRestTemplateFactory.getUserInfoRestTemplate();
        }
}
