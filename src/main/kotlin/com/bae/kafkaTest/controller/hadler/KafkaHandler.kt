package com.bae.kafkaTest.controller.hadler

import com.bae.kafkaTest.producer.KafkaProducer
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class KafkaHandler(val kafkaProducer: KafkaProducer) {

  fun sendMessage(serverRequest: ServerRequest): Mono<ServerResponse> {

    val message:String = serverRequest.queryParam("message").get()
    kafkaProducer.sendMessage(message)

    return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
        .body(BodyInserters.fromObject(message))

  }

}