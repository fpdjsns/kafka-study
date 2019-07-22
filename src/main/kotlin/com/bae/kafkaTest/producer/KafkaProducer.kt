package com.bae.kafkaTest.producer

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.stereotype.Component
import java.util.*

@Component
class KafkaProducer(val props: Properties = Properties()){

  val TOPIC_NAME = "bae-topic"

  init {
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  }

  fun sendMessage(message: String){
    val producer = KafkaProducer<String, String>(props)
    producer.send(ProducerRecord<String, String>(TOPIC_NAME, message))
    producer.close()
  }
}