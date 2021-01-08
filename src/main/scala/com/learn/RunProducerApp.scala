package com.learn

import com.learn.consumer.ReportConsumer
import com.learn.producer.ReportProducer

object RunProducerApp {


  def main(args: Array[String]): Unit = {
    ReportProducer.produce()
  }
}
