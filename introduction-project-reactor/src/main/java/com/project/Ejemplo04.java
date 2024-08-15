package com.project;

import reactor.core.publisher.Flux;

public class Ejemplo04 {
    public static void main(String[] args) {

        Flux<String> flux = Flux.fromArray(new String[] { "Data1", "Data2", "DataN" });

        System.out.println("Primera forma de impresion");
        flux.subscribe(System.out::println);

        System.out.println("Segunda forma de impresion");
        flux.doOnNext(System.out::println).subscribe();
    }
}
