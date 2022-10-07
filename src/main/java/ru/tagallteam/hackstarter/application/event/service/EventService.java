package ru.tagallteam.hackstarter.application.event.service;

import ru.tagallteam.hackstarter.application.common.CommonFilter;
import ru.tagallteam.hackstarter.application.common.Page;
import ru.tagallteam.hackstarter.application.event.modal.EventDto;

public interface EventService {
    Page<EventDto> events(CommonFilter filter);

    Page<EventDto> eventsUser(Long userId, CommonFilter filter);

    void createEventsFeedback(EventDto event);
}
