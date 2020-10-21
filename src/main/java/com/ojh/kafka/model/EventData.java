package com.ojh.kafka.model;

import com.ojh.kafka.model.vo.SubDataVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventData {
    private String eventName;
    private String data;
    private SubDataVO subDataVO;
    private int version;
}
