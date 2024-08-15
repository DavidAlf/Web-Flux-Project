package com.project;

import java.util.ArrayList;
import java.util.List;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Ejemplo01 {
    public static void main(String[] args) {

        // Mono(0-1) y Flux(1-n)
        System.out.println("Creacion de MONO y FLUX");

        // CREAR UN MONO
        Mono<Integer> mono = Mono.just(1991);
        List<Integer> elementFromMono = new ArrayList<>();

        // Nos suscribimos al mono
        mono.subscribe(elementFromMono::add);

        System.out.println(elementFromMono);

        // *********************************************//

        // CREAR UN FLUX
        Flux<Integer> flux = Flux.just(10, 18, 1991);
        List<Integer> elementFromFlux = new ArrayList<>();

        flux.subscribe(elementFromFlux::add);

        System.out.println(elementFromFlux);

    }
}