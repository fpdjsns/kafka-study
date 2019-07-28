package com.bae.kafkaTest.producer

import org.apache.kafka.clients.producer.Callback
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
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

  fun sendMessage(message: String): Boolean {
    val producer = KafkaProducer<String, String>(props)
    try {
      producer.send(ProducerRecord<String, String>(TOPIC_NAME, message), KafkaCallBack())
      return true
    } catch(e: Exception){
      e.printStackTrace()
    } finally {
      producer.close()
    }
    return false
  }

  private class KafkaCallBack : Callback {
    override fun onCompletion(metadata: RecordMetadata?, exception: java.lang.Exception?) {
      metadata?.let {
        print("""
          Partition : ${metadata.partition()}
          Offset : ${metadata.offset()}
        """.trimIndent())
      } ?: run {
        exception!!.printStackTrace()
      }
    }
  }
}