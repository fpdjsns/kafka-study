package com.bae.kafkaTest.controller

import com.bae.kafkaTest.producer.KafkaProducer
import org.springframework.web.bind.annotation.*

@RequestMapping(value = "/kafka")
@RestController
class kafkaController (val kafkaProducer: KafkaProducer){

  @PostMapping
  fun sendMessage(@RequestParam message: String) {
    kafkaProducer.sendMessage(message)
    return
  }
}


