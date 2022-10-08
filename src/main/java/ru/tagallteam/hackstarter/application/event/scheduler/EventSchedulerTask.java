package ru.tagallteam.hackstarter.application.event.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.tagallteam.hackstarter.application.event.domain.Event;
import ru.tagallteam.hackstarter.application.event.domain.EventRepository;
import ru.tagallteam.hackstarter.application.event.service.EventService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@EnableScheduling
public class EventSchedulerTask {

    private final EventService eventService;

    private final EventRepository eventRepository;


    /**
     * Удаляем старые ивенты
     */

    @Scheduled(cron = "0 0 */2 * * ?")
    public void removeOldEvent() {
        log.info("Scheduler started his work");
        List<Event> events = eventRepository.findAll();
        for (Event event : events)
            eventService.completeEvent(event.getId());
        log.info("Scheduler finished his work");
    }

}
