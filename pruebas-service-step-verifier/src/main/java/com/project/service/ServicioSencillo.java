package com.project.service;

import java.time.Duration;

import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ServicioSencillo {

    public Mono<String> buscarUno() {
        return Mono.just("Hola");
    }

    public Flux<String> buscarTodos() {
        return Flux.just("Hola", "que", "tal", "David");
    }

    public Flux<String> buscarTodosLento() {
        return Flux.just("Hola", "que", "tal", "David")
                .delaySequence(Duration.ofSeconds(10));
    }
}
