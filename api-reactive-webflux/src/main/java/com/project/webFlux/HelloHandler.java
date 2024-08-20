package com.project.webFlux;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class HelloHandler {

    public Mono<ServerResponse> getMonoMensaje(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(
                        Mono.just("Bienvenido a mi programa con Handlers de WebFlux"), String.class);
    }

    @SuppressWarnings("deprecation")
    public Mono<ServerResponse> getFluxMensaje(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(
                        Flux.just("Bienvenido", "a", "mi", "programa", "con", "Handlers", "de", "WebFlux")
                                .delayElements(Duration.ofSeconds(1)),
                        String.class);
    }
}