package com.example.observation;

import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.observability.micrometer.Micrometer;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Random;
import java.util.function.Supplier;

/**
 * @author Pouya Khoshkhou
 * @since 05/18/2023
 */
@RequiredArgsConstructor
@Service
public class GreetingService {

    private final Supplier<Long> latency = () -> new Random().nextLong(500);
    private final InnerGreetingService innerGreetingService;
    private final ObservationRegistry registry;

    public Mono<Greeting> greeting(String name) {
        Long lat = latency.get();
        return Mono
                .just(innerGreetingService.getGreeting(name))
                .delayElement(Duration.ofMillis(lat))
                .name("greeting.call")        //1
                .tag("latency", lat > 250 ? "high" : "low")    //2
                .tap(Micrometer.observation(registry));    //3
    }
}
