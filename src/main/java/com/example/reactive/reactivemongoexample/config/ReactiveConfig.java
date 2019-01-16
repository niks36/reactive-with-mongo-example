package com.example.reactive.reactivemongoexample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import com.example.reactive.reactivemongoexample.handlers.RouterHandlers;

@Configuration
public class ReactiveConfig {

    @Bean
    RouterFunction<?> routerFunction(RouterHandlers routerHandlers) {
        return RouterFunctions.route(RequestPredicates.GET("/employees/all"), routerHandlers::getAll)
                .andRoute(RequestPredicates.GET("/employees/{id}"), routerHandlers::getId)
                .andRoute(RequestPredicates.GET("/employees/{id}/events"), routerHandlers::getEvents);
    }
}
