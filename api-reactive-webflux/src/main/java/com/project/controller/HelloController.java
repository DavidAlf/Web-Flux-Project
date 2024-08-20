package com.project.controller;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/mono")
    public Mono<String> getMono() {
        return Mono.just("Bienvenidos a mi programa");
    }

    @SuppressWarnings("deprecation")
    @GetMapping(path = "/flux", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<String> getFlux() {
        Flux<String> mensaje = Flux.just("Bienvenidos", "a", "mi", "programa").delayElements(Duration.ofSeconds(1))
                .log();
        return mensaje;
    }
}
