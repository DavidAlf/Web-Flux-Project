package com.project;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Ejemplo03 {
    public static void main(String[] args) {
        Flux.fromArray(new String[] { "Tom", "Melisa", "Steven", "Megan" })
                .flatMap(Ejemplo03::nombreModificadoEnMono)
                .subscribe(System.out::println);
    }

    private static Mono<String> nombreModificadoEnMono(String nombre) {
        return Mono.just(nombre.concat(" Modificado"));
    }
}  
