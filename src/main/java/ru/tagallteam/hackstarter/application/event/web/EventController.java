package ru.tagallteam.hackstarter.application.event.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.tagallteam.hackstarter.application.common.Endpoints;
import ru.tagallteam.hackstarter.application.common.filter.CommonFilter;
import ru.tagallteam.hackstarter.application.common.filter.Page;
import ru.tagallteam.hackstarter.application.event.modal.EventDto;
import ru.tagallteam.hackstarter.application.event.service.EventService;
import ru.tagallteam.hackstarter.errors.annotations.SystemError;

@Api(tags = "Работа с мероприятиями")
@RestController
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @ApiOperation(value = "Получение мероприятий",
            notes = "Получение мероприятий ")
    @SystemError
    @PostMapping(Endpoints.EventService.EVENTS)
    public Page<EventDto> events(@RequestBody CommonFilter filter) {
        return eventService.events(filter);
    }

    @ApiOperation(value = "Получение мероприятий пользователя",
            notes = "Получение мероприятий пользователя, на который он вступил")
    @SystemError
    @PostMapping(Endpoints.EventService.EVENTS_USER)
    public Page<EventDto> eventsUser(@PathVariable Long userId, @RequestBody CommonFilter filter) {
        return eventService.eventsUser(userId, filter);
    }

    @ApiOperation(value = "Создание мероприятия",
            notes = "Создание мероприятия")
    @SystemError
    @PostMapping(Endpoints.EventService.EVENT_FEEDBACK)
    public void createEventsFeedback(@RequestBody EventDto event) {
        eventService.createEventsFeedback(event);
    }

    @ApiOperation(value = "Запись на мероприятия",
            notes = "Создание мероприятия и отправка на модерацию")
    @SystemError
    @PutMapping(Endpoints.EventService.SIGN_UP_FOR_EVENT)
    public void signInForEvent(@PathVariable Long eventId) {
        eventService.signInForEvent(eventId);
    }

}
