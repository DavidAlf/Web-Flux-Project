package com.project;

import reactor.core.publisher.Flux;

public class Ejemplo04 {
    public static void main(String[] args) {

        Flux<String> flux1 = Flux.fromArray(new String[] { "a", "b", "c" });

        Flux<String> flux2 = Flux.fromArray(new String[] { "d", "e", "f" });

        Flux<String> fluxConvinado = Flux.concat(flux1, flux2);

        fluxConvinado.subscribe(element -> System.out.println(element + " "));
    }
}
