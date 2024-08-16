import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class EjemplosTest {

    @Test
    public void testTransUsingMap() {
        List<String> nombresList = Arrays.asList("Google", "Facebook", "Instagram");

        Flux<String> nombresFlux = Flux.fromIterable(nombresList)
                .filter(nombre -> nombre.length() > 6)
                .map(nombre -> nombre.toUpperCase())
                .log();

        StepVerifier.create(nombresFlux).expectNext("FACEBOOK").expectNext("INSTAGRAM").verifyComplete();
    }

    @Test
    public void testTransUsingFlatMap() {
        List<String> nombresList = Arrays.asList("Google", "Facebook", "Instagram");

        Flux<String> nombresFlux = Flux.fromIterable(nombresList)
                .filter(nombre -> nombre.length() > 6)
                .flatMap(nombre -> {
                    return Mono.just(nombre.toUpperCase());
                })
                .log();

        StepVerifier.create(nombresFlux).expectNext("FACEBOOK").expectNext("INSTAGRAM").verifyComplete();
    }

    @Test
    public void testCombinarFlujosConMerge() {
        Flux<String> bebidas = Flux.just("Gatorade", "Sporade", "Agua de panela");
        Flux<String> comidas = Flux.just("Pasta", "Frijoles", "lentejas");

        Flux<String> megFlux = Flux.merge(bebidas, comidas).log();

        StepVerifier.create(megFlux).expectNext("Gatorade").expectNext("Sporade").expectNext("Agua de panela")
                .expectNext("Pasta").expectNext("Frijoles").expectNext("lentejas").verifyComplete();

    }

    @Test
    public void testCombinarFlujosConMergeDelay() {
        Flux<String> bebidas = Flux.just("Gatorade", "Sporade", "Agua de panela").delayElements(Duration.ofSeconds(1));
        Flux<String> comidas = Flux.just("Pasta", "Frijoles", "lentejas").delayElements(Duration.ofSeconds(1));

        Flux<String> megFlux = Flux.merge(bebidas, comidas).log();

        StepVerifier.create(megFlux)
                .expectSubscription()
                .expectNextCount(6).verifyComplete();

    }
    

    @Test
    public void testConvinarFlujosConConcat() {
        Flux<String> bebidas = Flux.just("Gatorade", "Sporade", "Agua de panela");
        Flux<String> comidas = Flux.just("Pasta", "Frijoles", "lentejas");

        Flux<String> concatFlux = Flux.concat(bebidas, comidas).log();

        StepVerifier.create(concatFlux).expectNext("Gatorade").expectNext("Sporade").expectNext("Agua de panela")
                .expectNext("Pasta").expectNext("Frijoles").expectNext("lentejas").verifyComplete();
    }

    @Test
    public void testConvinarFlujosConZip() {
        Flux<String> bebidas = Flux.just("Gatorade", "Sporade", "Agua de panela");
        Flux<String> comidas = Flux.just("Pasta", "Frijoles", "lentejas");

        Flux<String> zipFlux = Flux.zip(bebidas, comidas,(f1,f2)->{
            return f1.concat(" ").concat(f2);
        }).log();

        StepVerifier.create(zipFlux).expectNext("Gatorade Pasta","Sporade Frijoles","Agua de panela lentejas").verifyComplete();
    }
}
