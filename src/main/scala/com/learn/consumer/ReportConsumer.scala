package com.learn.consumer

import java.time.Duration
import java.util
import java.util.Properties

import com.learn.constants.KafkaParamters
import com.learn.customObject.Report
import com.learn.serde.ReportDeserializer
import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}
import org.apache.kafka.common.serialization.StringDeserializer

import scala.collection.JavaConverters._
object ReportConsumer {

  def consumer(): Unit ={

    val props=new Properties()
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,KafkaParamters.KAFKA_BROKERS)
    props.put("group.id", KafkaParamters.GROUP_ID_CONFIG)
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,classOf[StringDeserializer].getName)
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,classOf[ReportDeserializer].getName)
    props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, KafkaParamters.MAX_POLL_RECORDS)
    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false")
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, KafkaParamters.OFFSET_RESET_LATEST)

    val KConsumer=new KafkaConsumer[String,Report](props)

    val topics=new util.ArrayList[String]()
    topics.add(KafkaParamters.TOPIC_NAME)
    KConsumer.subscribe(topics)

    try{
      while (true){
        val CRecords= KConsumer.poll(Duration.ofMillis(10))
        CRecords.asScala.foreach(cr=>{
          println("Report : "+ cr.value().toString)
        })
        KConsumer.commitSync()
      }
    }
    catch {
      case e:Exception => throw new RuntimeException(e)
    }
    finally {
      KConsumer.close()
    }

  }

}
