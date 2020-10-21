package com.ojh.kafka.example;

import com.ojh.kafka.model.EventData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class KafkaListenersExample {

//    @KafkaListener(topics = "reflectoring-1")
//    void listener(String data) {
//        log.info(data);
//    }

    @KafkaListener(
//            topics = {"reflectoring-1", "reflectoring-2"},
            topicPartitions
                    = @TopicPartition(topic = "reflectoring-1", partitions = {"0"}),
            groupId = "${spring.kafka.consumer.group-id}")
    void commonListenerForMultipleTopics(@Payload EventData eventData,
                                         @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                                         @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                                         @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.info("Topic {} (Partition {})- {} with key value = {}", topic, partition, eventData.getData(), key);
    }

    @KafkaListener(
            topicPartitions
                    = @TopicPartition(topic = "reflectoring-1", partitions = {"1"}),
            groupId = "${spring.kafka.consumer.group-id}")
    void commonListenerForMultipleTopics1(@Payload EventData eventData,
                                          @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                                          @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                                          @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.info("Topic {} (Partition {})- {} with key value = {}, SubData = {},{}", topic, partition, eventData.getData(), key,
                eventData.getSubDataVO().getId(), eventData.getSubDataVO().getValue());
    }
}
