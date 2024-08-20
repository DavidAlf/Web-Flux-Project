package com.project.funtional;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ContactoRouter {

    @Bean
    public RouterFunction<ServerResponse> routeContacto(ContactoHandler contactoHandler) {
        return RouterFunctions
                .route(GET("/funtional/contactos"), contactoHandler::getAll)
                .andRoute(GET("/funtional/contactos/{id}"), contactoHandler::getById)
                .andRoute(GET("/funtional/contactos/byEmail/{email}"), contactoHandler::getByEmail)
                .andRoute(POST("/funtional/contactos"), contactoHandler::saveContacto)
                .andRoute(PUT("/funtional/contactos/{id}"), contactoHandler::updateContacto)
                .andRoute(DELETE("/funtional/contactos/{id}"), contactoHandler::deleteContacto);

    }
}
