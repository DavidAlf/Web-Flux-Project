package com.project.Usuario;

import reactor.core.publisher.Flux;

public class TestUsuarios {

    public static void main(String[] args) {
        System.out.println("Inicio de flux con objetos");
        
        Flux<String> nombres = Flux.just("David Alfonso", "Andres Alfonso", "Daniela Alfonso", "Mari Ortiz");

        Flux<Usuario> usurios = nombres
                .map(nombre -> new Usuario(nombre.split(" ")[0].toUpperCase(), nombre.split(" ")[1].toUpperCase()))
                .filter(usuario -> !usuario.getApellido().equalsIgnoreCase("Alfonso"))
                .doOnNext(usuario -> {
                    if (usuario == null) {
                        throw new RuntimeException("Los nombres no pueden estar vacios");
                    }

                    System.out.println(usuario.getNombre().concat(" ").concat(usuario.getApellido()));
                }).map(usuario -> {
                    String nombre = usuario.getNombre().toLowerCase();
                    usuario.setNombre(nombre);

                    return usuario;
                });

        usurios.subscribe(e -> System.out.println(e.toString()), error -> System.err.println(error.getMessage()),
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Se a finalizado la ejecucion del observable con exito");
                    }
                });
    }
}
