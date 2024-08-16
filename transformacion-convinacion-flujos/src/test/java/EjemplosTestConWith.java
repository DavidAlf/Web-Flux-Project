import java.time.Duration;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class EjemplosTestConWith {

    @Test
    public void testRetornarMerge() {
        StepVerifier.create(retornarMerge())
                .expectNext("c")
                .expectNext("a")
                .expectNext("b")
                .expectNext("d")
                .expectComplete()
                .verify();
    }

    private static Flux<String> retornarMerge() {

        Flux<String> flux1 = Flux.fromArray(new String[] { "a", "b" }).delayElements(Duration.ofMillis(10));
        Flux<String> flux2 = Flux.fromArray(new String[] { "c", "d" }).delayElements(Duration.ofMillis(10));

        return flux1.mergeWith(flux2);
    }

}
