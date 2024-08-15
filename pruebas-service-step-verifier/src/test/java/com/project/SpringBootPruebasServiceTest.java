package com.project;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.project.service.ServicioSencillo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class SpringBootPruebasServiceTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ServicioSencillo servicioSencillo;

    @Test
    void contextLoads() {
        assertThat(applicationContext).isNotNull();
    }

    @Test
    void mainMethodRunsSuccessfully() {
        SpringBootPruebasService.main(new String[] {});
    }

    @Test
    public void testMonoBuscarUno() {

        Mono<String> uno = servicioSencillo.buscarUno();

        StepVerifier.create(uno).expectNext("Hola").expectComplete().verify();
    }

    @Test
    public void testFluxTodos() {
        Flux<String> varios = servicioSencillo.buscarTodos();

        StepVerifier.create(varios).expectNext("Hola").expectNext("que").expectNext("tal").expectNext("David")
                .expectComplete().verify();
    }

    @Test
    public void testFluxTodoLento() {
        Flux<String> varios = servicioSencillo.buscarTodosLento();

        StepVerifier.create(varios).expectNext("Hola")
                .thenAwait(Duration.ofSeconds(1)).expectNext("que")
                .thenAwait(Duration.ofSeconds(1)).expectNext("tal")
                .thenAwait(Duration.ofSeconds(1)).expectNext("David")
                .thenAwait(Duration.ofSeconds(1)).verifyComplete();

    }
}
