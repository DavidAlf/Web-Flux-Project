package com.project;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Ejemplo05 {

    public static void main(String[] args) {

        Flux<String> flux1 = Flux.fromArray(new String[] { "a", "b", "c" });

        Mono<String> mono = Mono.just("f");

        Flux<String> fluxConvinado = flux1.concatWith(mono);

        fluxConvinado.subscribe(element -> System.out.println(element + " "));
    }
}
