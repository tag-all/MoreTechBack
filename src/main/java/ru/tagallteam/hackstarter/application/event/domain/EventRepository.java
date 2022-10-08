package ru.tagallteam.hackstarter.application.event.domain;

import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.tagallteam.hackstarter.application.user.domain.User;

public interface EventRepository extends JpaRepository<Event, Long> {

    Page<Event> findAllByTopicContains(String name, Pageable pageable);

    Page<Event> findAllByUsersIn(List<User> users, Pageable pageable);

}
