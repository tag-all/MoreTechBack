package ru.tagallteam.hackstarter.application.event.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    Page<Event> findEventsByTopicContains(String name, Pageable pageable);
    @Query("select u.events from User u where u.id = ?1 ")
    Page<Event> findUserEventsByUserId(Long user_id, Pageable pageable);
    @Query("select u.events from User u where u.id = ?1 and u.events = " +
            "(select e from Event e where e.topic like ?2)")
    Page<Event> findUserEventsByUserIdWhichContains(Long user_id, String topic, Pageable pageable);
}
