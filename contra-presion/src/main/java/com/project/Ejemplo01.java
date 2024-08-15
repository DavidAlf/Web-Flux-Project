package com.project;

import java.util.ArrayList;
import java.util.Arrays;

import reactor.core.publisher.Flux;

public class Ejemplo01 {
    public static void main(String[] args) {

        //Flujo normal sin contrapresion
        Flux<String> ciudades = Flux.fromIterable(new ArrayList<>(Arrays.asList("Bogota", "Cali", "Bucaramanga")));

        ciudades.log().subscribe();
    }
}