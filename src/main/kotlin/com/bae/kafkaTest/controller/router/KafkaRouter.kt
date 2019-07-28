package com.bae.kafkaTest.controller.router

import com.bae.kafkaTest.controller.hadler.KafkaHandler
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.HandlerFunction
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.RouterFunction



@Configuration
class KafkaRouter(private val kafkaHandler: KafkaHandler) {

  @Bean
  fun route(): RouterFunction<ServerResponse> {

    return RouterFunctions
        .route(RequestPredicates.POST("/kafka").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
            HandlerFunction<ServerResponse> { kafkaHandler.sendMessage(it) })
  }

}