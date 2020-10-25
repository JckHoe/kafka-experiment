package com.ojh.kafka.controller;

import com.ojh.kafka.model.EventData;
import com.ojh.kafka.model.vo.SubDataVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KafkaController {
    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    private final KafkaTemplate<String, EventData> kafkaTemplate;

    @GetMapping("/send/{id}")
    public void doSendKafkaTopic(@PathVariable("id") String id) {
            kafkaTemplate.send("reflectoring-1",
//                    Integer.valueOf(id),
                    id,
                    EventData.builder()
                            .eventName("Event1")
                            .data("Hi Rem! <3 [{"+id+"}]")
                            .version(1)
                            .build()
            );
    }
}
