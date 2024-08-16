package com.project;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;

@Repository
public class ProductoRepository {

    private static List<Producto> listaProducto = new ArrayList<>();

    private static List<Producto> listaProducto2 = new ArrayList<>();

    static {
        listaProducto.add(new Producto(1, "Ordenador", 900));
        listaProducto.add(new Producto(2, "Table", 300));
        listaProducto.add(new Producto(3, "Auricular", 300));

        listaProducto2.add(new Producto(4, "Movil", 600));
        listaProducto2.add(new Producto(5, "Teclado", 50));
        listaProducto2.add(new Producto(6, "Raton", 20));
    }

    public Flux<Producto> buscarTodos() {
        return Flux.fromIterable(listaProducto).delayElements(Duration.ofSeconds(3));
    }

    public Flux<Producto> buscarOtros() {
        return Flux.fromIterable(listaProducto2).delayElements(Duration.ofSeconds(3));
    }

}
