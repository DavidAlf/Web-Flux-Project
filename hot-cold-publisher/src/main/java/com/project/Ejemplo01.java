package com.project;

import java.time.Duration;
import java.util.stream.Stream;

import reactor.core.publisher.Flux;

public class Ejemplo01 {
    public static void main(String[] args) throws InterruptedException {

        Flux<String> netFlux = Flux.fromStream(Ejemplo01::getVideo)
                .delayElements(Duration.ofSeconds(2));

        netFlux.subscribe(part -> System.out.println("Suscriber 1 " + part));

        Thread.sleep(5000);

        netFlux.subscribe(part -> System.out.println("Suscriber 2 " + part));

        Thread.sleep(6000);
    }

    private static Stream<String> getVideo() {
        System.out.println("Request para el video");

        return Stream.of("Part 1", "Part 2", "Part 3", "Part 4", "Part 5", "Part n");
    }
}