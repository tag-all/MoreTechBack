package ru.tagallteam.hackstarter.application.event.mapper;

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
    public Event convertToEvent(EventDto eventDto) {
        Event event = new Event();
        event.setTopic(eventDto.getTopic());
        event.setDescription(eventDto.getDescription());
        event.setEventTime(eventDto.getEventTime());
        if (!CollectionUtils.isEmpty(eventDto.getAttribute())) {
            List<EventAttribute> attributes = new ArrayList<>(Collections.emptyList());
            for (EventAttributeType type : eventDto.getAttribute().keySet()) {
                attributes.add(convertToEventAttribute(event, type, eventDto.getAttribute().get(type)));
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
        eventAttribute.setValue(type.fromValue(val));
        return eventAttribute;
    }
}
