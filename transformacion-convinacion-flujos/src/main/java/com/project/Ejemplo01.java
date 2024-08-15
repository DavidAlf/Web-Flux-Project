package com.project;

import reactor.core.publisher.Flux;

public class Ejemplo01 {
    public static void main(String[] args) {

        Flux.fromArray(new String[] { "Tom", "Melisa", "Steven", "Megan" }).map(String::toUpperCase)
                .subscribe(System.out::println);
    }
}