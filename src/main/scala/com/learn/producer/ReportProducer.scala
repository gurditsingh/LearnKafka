package com.learn.producer

import java.util.Properties

import com.learn.CustomPartitioner.ReportPartitioner
import com.learn.constants.KafkaParamters
import com.learn.customObject.Report
import com.learn.serde.ReportSerializer
import org.apache.commons.lang3.RandomStringUtils
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer

object ReportProducer {

  def produce(): Unit ={

    val props=new Properties()
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,KafkaParamters.KAFKA_BROKERS)
    props.put(ProducerConfig.CLIENT_ID_CONFIG,KafkaParamters.CLIENT_ID)
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,classOf[StringSerializer].getName)
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,classOf[ReportSerializer].getName)
//    props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,classOf[ReportPartitioner].getName)


    val KProducer=new KafkaProducer[String,Report](props)

    try{
      for(i <- 1 to KafkaParamters.MESSAGE_COUNT){
        val report=new Report(i,RandomStringUtils.randomAlphabetic(4),RandomStringUtils.randomAlphabetic(4)
          ,RandomStringUtils.randomAlphanumeric(10))
        val PRecord =new ProducerRecord[String,Report](KafkaParamters.TOPIC_NAME,i.toString,report)

        val recordMetadata= KProducer.send(PRecord).get()
        println("Record timeStamp : "+recordMetadata.timestamp()+", Record partition : "+recordMetadata.partition())
      }}
    catch {
      case e:Exception => throw new RuntimeException(e)
    }
    finally {
      KProducer.close()
    }
  }

}
