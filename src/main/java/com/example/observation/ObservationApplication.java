package com.example.observation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import reactor.core.publisher.Hooks;

/**
 * Webflux spring boot 3 application demonstrates how to propagate traceId throw web service calls using WebClient
 *
 * @author Pouya Khoshkhou
 * @since 05/18/2023
 */
@SpringBootApplication
@Slf4j
public class ObservationApplication {

    public static void main(String[] args) {
        Hooks.enableAutomaticContextPropagation();
        new SpringApplicationBuilder()
                .web(WebApplicationType.REACTIVE)
                .build()
                .run(ObservationApplication.class, args);
    }
}




