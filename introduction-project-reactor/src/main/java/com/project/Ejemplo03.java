package com.project;

import reactor.core.publisher.Mono;

public class Ejemplo03 {
    public static void main(String[] args) {

        Mono<String> mono = Mono.fromSupplier(
                () -> {
                    throw new RuntimeException("Mostrando una excepcion");
                });

        mono.subscribe(
                data -> System.out.println(data), // onNext -> referencia para pasar al elemento
                err -> System.out.println(err), // onError
                () -> System.out.println("Completado !") // onComplete
        );
    }
}
