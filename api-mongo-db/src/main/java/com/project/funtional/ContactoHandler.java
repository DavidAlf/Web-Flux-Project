package com.project.funtional;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.project.documents.Contacto;
import com.project.repository.ContactoRepository;

import reactor.core.publisher.Mono;

@Component
public class ContactoHandler {

    @Autowired
    private ContactoRepository contactoRepository;

    private Mono<ServerResponse> response404 = ServerResponse.notFound().build();

    private Mono<ServerResponse> response406 = ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).build();

    ContactoHandler() {

    }

    public Mono<ServerResponse> getAll(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(contactoRepository.findAll(), Contacto.class);
    }

    public Mono<ServerResponse> getById(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");

        return contactoRepository.findById(id)
                .flatMap(contacto -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(contacto)))
                .switchIfEmpty(response404);
    }

    public Mono<ServerResponse> getByEmail(ServerRequest serverRequest) {
        String email = serverRequest.pathVariable("email");

        return contactoRepository.findFirstByEmail(email)
                .flatMap(contacto -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(contacto)))
                .switchIfEmpty(response404);
    }

    public Mono<ServerResponse> saveContacto(ServerRequest serverRequest) {
        Mono<Contacto> contactoMono = serverRequest.bodyToMono(Contacto.class);

        return contactoMono
                .flatMap(contacto -> contactoRepository.save(contacto)
                        .flatMap(contactoSaved -> ServerResponse.accepted()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(fromValue(contactoSaved))))
                .switchIfEmpty(response404);
    }

    public Mono<ServerResponse> updateContacto(ServerRequest serverRequest) {
        Mono<Contacto> contactoMono = serverRequest.bodyToMono(Contacto.class);

        String id = serverRequest.pathVariable("id");

        Mono<Contacto> contactoActualizado = contactoMono
                .flatMap(contacto -> contactoRepository.findById(id)
                        .flatMap(oldContacto -> {
                            oldContacto.setTelefono(contacto.getTelefono());
                            oldContacto.setNombre(contacto.getNombre());
                            oldContacto.setEmail(contacto.getEmail());

                            return contactoRepository.save(oldContacto);
                        }));

        return contactoActualizado
                .flatMap(contacto -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(contacto)))
                .switchIfEmpty(response406);
    }

    public Mono<ServerResponse> deleteContacto(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");

        Mono<Void> contactoDelete = contactoRepository.deleteById(id);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(contactoDelete, Void.class);
    }
}
