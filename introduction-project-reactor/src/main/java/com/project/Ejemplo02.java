package com.project;

import reactor.core.publisher.Mono;

public class Ejemplo02 {
    public static void main(String[] args) {
        System.out.println("EJEMPLO MONO");

        Mono<String> mono = Mono.just("Hola David");
        mono.subscribe(
                data -> System.out.println(data), // onNext -> referencia para pasar al elemento
                err -> System.out.println(err), // onError
                () -> System.out.println("Completado !") // onComplete
        );

    }
}
