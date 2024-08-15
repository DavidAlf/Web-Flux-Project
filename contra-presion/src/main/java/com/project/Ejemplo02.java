package com.project;

import org.reactivestreams.Subscription;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class Ejemplo02 {
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void main(String[] args) {

        // Flujo normal con contrapresion(Capasidad de indicar la cantidad de elementos
        // que se van a traer en un flujo)
        Flux<Integer> publisher = Flux.range(1, 100).log();

        publisher.subscribe(new BaseSubscriber() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                request(20);
            }

            @Override
            protected void hookOnNext(Object value) {
                if ((Integer) value == 5) {
                    cancel();                    
                }
            }
        });

    }
}
