package com.bae.kafkaTest.controller

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class kafkaControllerTest() {

  @Autowired
  lateinit var webTestClient : WebTestClient

  @Test
  fun sendMessageTest() {
    val message = "sendTest"
    val result = webTestClient.post()
        .uri("/kafka?message=$message")
        .exchange()
        .expectStatus().isOk()
        .expectBody(String::class.java)
        .returnResult()
        .responseBody
    Assert.assertEquals(message, result)
  }

}