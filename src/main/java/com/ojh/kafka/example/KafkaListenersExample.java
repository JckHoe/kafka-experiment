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

    @KafkaListener(
            topics = {"reflectoring-1", "reflectoring-2"},
            groupId = "${spring.kafka.consumer.group-id}")
    void commonListenerForMultipleTopics(@Payload EventData eventData,
                                         @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                                         @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                                         @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) throws Exception{
        log.info("Topic {} (Partition {})- {} with key value = ", topic, partition, eventData.getData());
    }
}
