package ru.tagallteam.hackstarter.application.event.modal;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class EventDto {
    private Long id;
    private String topic;

    private LocalDateTime eventTime;

    private String description;

    private Map<EventAttributeType, Object> attributes;
}
