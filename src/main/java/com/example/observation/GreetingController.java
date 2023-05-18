package com.example.observation;

import io.micrometer.common.KeyValue;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Pouya Khoshkhou
 * @since 05/18/2023
 */
@RequiredArgsConstructor
@RestController
@Slf4j
public class GreetingController {
    private final GreetingService service;
    private final ObservationRegistry registry;
    @Value("${server.port}")
    private String port;

    private WebClient getWebClient() {
        HttpClient httpClient = HttpClient.create().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .responseTimeout(Duration.ofMillis(5000))
                .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));

        return WebClient.builder().baseUrl("http://localhost:" + port).observationRegistry(registry)
                .clientConnector(new ReactorClientHttpConnector(httpClient)).build();
    }

    @GetMapping("/hello/{name}")
    public Mono<Greeting> greeting(@PathVariable("name") String name, @RequestHeader Map<String, Object> headers) {
        log.info(headers.toString());
        return Observation.createNotStarted("greetingService", registry).highCardinalityKeyValue(KeyValue.of("name", "string")).observe(() -> {
            log.info("Generating a String...");
            return service.greeting(name);
        });
    }

    @GetMapping("/edge/hello/{name}")
    public Mono<Greeting> outerGreeting(@PathVariable("name") String name) {
        return Observation.createNotStarted("greetingService", registry).lowCardinalityKeyValue("name", "string").observe(() -> {
            log.info("Calling inner hello");
            return getWebClient().get().uri("/hello/" + name).retrieve().bodyToMono(Greeting.class);
        });
    }
}