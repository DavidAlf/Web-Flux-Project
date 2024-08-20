package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.documents.Contacto;
import com.project.repository.ContactoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class ContactoController {

    @Autowired
    private ContactoRepository contactoRepository;

    @GetMapping(value = "/contactos")
    public Flux<Contacto> listarContactos() {
        return contactoRepository.findAll();
    }

    @GetMapping(value = "/contactos/{id}")
    public Mono<ResponseEntity<Contacto>> getContactoById(@PathVariable String id) {
        return contactoRepository.findById(id)
                .map(contacto -> new ResponseEntity<>(contacto, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/contactos/byEmail/{email}")
    public Mono<ResponseEntity<Contacto>> getContactoByEmail(@PathVariable String email) {
        return contactoRepository.findFirstByEmail(email)
                .map(contacto -> new ResponseEntity<>(contacto, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/contactos")
    public Mono<ResponseEntity<Contacto>> saveContacto(@RequestBody Contacto contacto) {
        return contactoRepository.insert(contacto)
                .map(contactoSaved -> new ResponseEntity<>(contactoSaved, HttpStatus.CREATED))
                .defaultIfEmpty(new ResponseEntity<>(contacto, HttpStatus.NOT_ACCEPTABLE));
    }

    @PutMapping(value = "/contactos/{id}")
    public Mono<ResponseEntity<Contacto>> updateContacto(@PathVariable String id, @RequestBody Contacto contacto) {
        return contactoRepository.findById(id)
                .flatMap(contactoUpdated -> {
                    contacto.setId(id);

                    return contactoRepository.save(contacto)
                            .map(contacto1 -> new ResponseEntity<>(contacto1, HttpStatus.ACCEPTED));
                })
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/contactos/{id}")
    public Mono<Void> deleteContacto(@PathVariable String id) {
        return contactoRepository.deleteById(id);
    }

}
