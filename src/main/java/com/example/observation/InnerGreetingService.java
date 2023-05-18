package com.example.observation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Pouya Khoshkhou
 * @since 05/18/2023
 */
@Service
@Slf4j
public class InnerGreetingService {
    public Greeting getGreeting(String name) {
        log.info("create {}", name);
        return new Greeting(name);
    }
}
