package com.learn.serde

import com.learn.customObject.Report
import org.apache.kafka.common.serialization.Serializer
import com.fasterxml.jackson.databind.ObjectMapper

class ReportSerializer extends Serializer[Report]{
  override def serialize(topic: String, data: Report): Array[Byte] = {
    val objectMapper = new ObjectMapper
    try
      objectMapper.writeValueAsString(data).getBytes
    catch {
      case e: Exception => throw new RuntimeException(e)
    }
  }

}
