package ru.tagallteam.hackstarter.application.event.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.tagallteam.hackstarter.application.user.domain.User;

import java.util.List;

public interface EventUserRepository extends JpaRepository<EventUser, Long> {

    Page<EventUser> findEventUsersByUser(User user, Pageable pageable);

    List<EventUser> findEventUsersByEvent(Event event);
}
