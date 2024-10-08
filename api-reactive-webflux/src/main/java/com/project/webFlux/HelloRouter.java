package com.project.webFlux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class HelloRouter {

    @Bean
    public RouterFunction<ServerResponse> funtionalRoutes(HelloHandler helloHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/funtional/mono"), helloHandler::getMonoMensaje)
                .andRoute(RequestPredicates.GET("/funtional/flux"), helloHandler::getFluxMensaje);
    }
}
