package com.project;

import reactor.core.publisher.Flux;

public class Ejemplo03 {
    public static void main(String[] args) {
        Flux.just(2, 7, 10, 8, 0, 12, 22, 24)
                .map(element -> {
                    if (element == 0) {
                        throw new RuntimeException("Error Ocurre");
                    }

                    return element;
                })
                .onErrorContinue((ex, element) -> {
                    System.out.println("Exception: " + ex);
                    System.out.println("Elemento que genera la exception es: " + element);
                })
                .log()
                .subscribe();
    }
}
