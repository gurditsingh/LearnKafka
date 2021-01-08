package com.learn.constants

object KafkaParamters {

  var KAFKA_BROKERS = "localhost:9092"

  var MESSAGE_COUNT:Int = 10

  var CLIENT_ID = "KClient"

  var TOPIC_NAME = "reseting"

  var GROUP_ID_CONFIG = "KConsumerGroup"

  var MAX_NO_MESSAGE_FOUND_COUNT:Int = 100

  var OFFSET_RESET_LATEST = "latest"

  var OFFSET_RESET_EARLIER = "earliest"

  var MAX_POLL_RECORDS = "1"
}
