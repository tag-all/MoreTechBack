package ru.tagallteam.hackstarter.application.event.mapper;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import ru.tagallteam.hackstarter.application.event.domain.Event;
import ru.tagallteam.hackstarter.application.event.domain.EventAttribute;
import ru.tagallteam.hackstarter.application.event.modal.EventAttributeType;
import ru.tagallteam.hackstarter.application.event.modal.EventDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class EventMapper {

    public EventDto convertToEventDto(Event event){
        EventDto eventDto = new EventDto();
        eventDto.setTopic(event.getTopic());
        eventDto.setDescription(event.getDescription());
        eventDto.setEventTime(event.getEventTime());
        if (!CollectionUtils.isEmpty(event.getEventAttributes())) {
            Map<EventAttributeType, Object> attributes = new HashMap<>();
            event.getEventAttributes().forEach(it -> attributes.put(it.getType(),
                    it.getType().getValue(it.getValue())));
            eventDto.setAttributes(attributes);
        }
        return eventDto;
    }

    public Event convertToEvent(EventDto eventDto) {
        Event event = new Event();
        event.setTopic(eventDto.getTopic());
        event.setDescription(eventDto.getDescription());
        event.setEventTime(eventDto.getEventTime());
        if (!CollectionUtils.isEmpty(eventDto.getAttributes())) {
            List<EventAttribute> attributes = new ArrayList<>(Collections.emptyList());
            for (EventAttributeType type : eventDto.getAttributes().keySet()) {
                attributes.add(convertToEventAttribute(event, type, eventDto.getAttributes().get(type)));
            }
            event.setEventAttributes(attributes);
        } else {
            event.setEventAttributes(Collections.emptyList());
        }
        return event;
    }

    public EventAttribute convertToEventAttribute(Event event, EventAttributeType type, Object val) {
        EventAttribute eventAttribute = new EventAttribute();
        eventAttribute.setEvent(event);
        eventAttribute.setType(type);
        eventAttribute.setValue(type.fromValue(val).toUpperCase());
        return eventAttribute;
    }
}
