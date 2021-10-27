package com.toy.pbpostbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import java.lang.annotation.Annotation;

@SpringBootApplication
@RefreshScope
@EnableFeignClients
@EnableCircuitBreaker
@EnableResourceServer
@EnableJpaAuditing
public class PbpostboxApplication {
    public static void main(String[] args) {
        SpringApplication.run(PbpostboxApplication.class, args);
    }
}
