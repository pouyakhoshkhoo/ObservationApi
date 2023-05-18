package com.example.observation;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Pouya Khoshkhou
 * @since 05/18/2023
 */
@NoArgsConstructor
@Data
@Slf4j
public class Greeting {

    private String name;

    public Greeting(String name) {
        this.name = name;
        log.info("create greeting");
    }
}