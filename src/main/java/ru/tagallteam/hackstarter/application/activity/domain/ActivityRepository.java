package ru.tagallteam.hackstarter.application.activity.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tagallteam.hackstarter.application.user.domain.User;

import java.time.LocalDateTime;
import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findActivitiesByUser(User user);

    List<Activity> findActivitiesByActivityDateBeforeOrderByActivityDateDesc(LocalDateTime dateBefore);

}
