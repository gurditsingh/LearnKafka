package com.learn.serde

import com.learn.customObject.Report
import org.apache.kafka.common.serialization.Deserializer
import com.fasterxml.jackson.databind.ObjectMapper


class ReportDeserializer extends Deserializer[Report]{
  override def deserialize(topic: String, data: Array[Byte]): Report = {

    val mapper = new ObjectMapper
    try {
      mapper.readValue(data, classOf[Report])
    }catch {
      case e: Exception => throw new RuntimeException(e)
    }
  }
}
