package sample

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer

object KProducer {

  def main(args: Array[String]): Unit = {

    val props=new Properties()
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092")
    props.put(ProducerConfig.CLIENT_ID_CONFIG,"LearnKafkaProducer")
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,classOf[StringSerializer].getName)
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,classOf[StringSerializer].getName)


    val KProducer=new KafkaProducer[String,String](props)

    try{
    for(i <- 1 to 10){
      val PRecord =new ProducerRecord[String,String]("learn_kafka","Mags : "+i)
      KProducer.send(PRecord)
    }}
    catch {
      case e:Exception => throw new RuntimeException(e)
    }
    finally {
      KProducer.close()
    }

  }
}
