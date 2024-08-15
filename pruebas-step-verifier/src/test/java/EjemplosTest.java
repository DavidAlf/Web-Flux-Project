import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class EjemplosTest {

    @Test
    public void testFlux() {
        Flux<Integer> fluxToTest = Flux.just(1, 2, 3, 4, 5);

        // Crear la prueba
        StepVerifier.create(fluxToTest).expectNext(1).expectNext(2).expectNext(3).expectNext(4).expectNext(5)
                .expectComplete().verify();
    }

    @Test
    public void testFluxString() {

        Flux<String> fluxToTest = Flux.just("David", "Andres", "Daniela", "Mari", "Ahri", "Odi", "Mia")
                .filter(nombre -> nombre.length() <= 4)
                .map(String::toUpperCase);

        StepVerifier.create(fluxToTest).expectNext("MARI").expectNext("AHRI")
                .expectNextMatches(nombre -> nombre.startsWith("O"))
                .expectNext("MIA").expectComplete().verify();

    }
}