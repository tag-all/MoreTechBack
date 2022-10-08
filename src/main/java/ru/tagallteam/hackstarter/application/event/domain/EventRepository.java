package ru.tagallteam.hackstarter.application.event.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

    Page<Event> findEventsByTopicContains(String name, Pageable pageable);

}
