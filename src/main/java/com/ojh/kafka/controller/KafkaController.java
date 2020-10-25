package com.ojh.kafka.controller;

import com.ojh.kafka.model.EventData;
import com.ojh.kafka.model.vo.SubDataVO;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
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
        if(id.equals("1")) {
            kafkaTemplate.send("reflectoring-1",
                    0,
                    "SOME_KEY",
                    EventData.builder()
                            .eventName("Event1")
                            .data("Hi Rem! <3")
                            .version(1)
                            .build()
            );
        } else if (id.equals("2")){
            kafkaTemplate.send("reflectoring-1",
                    1,
                    "SOME_KEY",
                    EventData.builder()
                            .eventName("Event1")
                            .data("Hi Rem! <3")
                            .version(1
                            )
                            .subDataVO(SubDataVO.builder()
                                    .id("ID")
                                    .value("Rem is better than Emilia")
                                    .build())
                            .build()
            );
        }
    }
}
