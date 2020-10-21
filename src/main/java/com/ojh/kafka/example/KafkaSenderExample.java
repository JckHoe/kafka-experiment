package com.ojh.kafka.example;

import com.ojh.kafka.model.EventData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
class KafkaSenderExample {

    private final KafkaTemplate<String, EventData> kafkaTemplate;

    @Autowired
    KafkaSenderExample(KafkaTemplate<String, EventData> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    void sendMessage(EventData message, String topicName) {
        kafkaTemplate.send(topicName, message);
    }

    void sendMessageWithCallback(EventData message) {
        ListenableFuture<SendResult<String, EventData>> future =
                kafkaTemplate.send("", message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, EventData>>() {
            @Override
            public void onSuccess(SendResult<String, EventData> result) {
                log.info("Message [{}] delivered with offset {}",
                        message,
                        result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.warn("Unable to deliver message [{}]. {}",
                        message,
                        ex.getMessage());
            }
        });
    }
}
