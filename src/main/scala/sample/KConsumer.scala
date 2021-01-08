package sample

import java.time.Duration
import java.util
import java.util.Properties

import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}
import org.apache.kafka.common.serialization.StringDeserializer

import scala.collection.JavaConversions._
object KConsumer {

  def main(args: Array[String]): Unit = {


    val props=new Properties()
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092")
    props.put("group.id", "Learn-Consumer")
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,classOf[StringDeserializer].getName)
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,classOf[StringDeserializer].getName)


    val KConsumer=new KafkaConsumer[String,String](props)

    val topics=new util.ArrayList[String]()
    topics.add("learn_kafka")
    KConsumer.subscribe(topics)


    try{
      while (true){

        val CRecords= KConsumer.poll(Duration.ofMillis(10))
        CRecords.foreach(cr=>{
          println("Record : "+ cr.value())
        })
//        KConsumer.commitAsync()
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
