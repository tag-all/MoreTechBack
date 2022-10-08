package ru.tagallteam.hackstarter.application.activity.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.tagallteam.hackstarter.application.user.domain.User;

import java.time.LocalDateTime;
import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findActivitiesByUser(User user);

    Page<Activity> findActivitiesByUser_Id(Long id, Pageable pageable);

    List<Activity> findActivitiesByActivityDateBeforeOrderByActivityDateDesc(LocalDateTime dateBefore);

    Page<Activity> findAllByTypeContains(String type, Pageable Pageable);

    Page<Activity> findAllByTypeContainsAndUser_Id(String type, Long id, Pageable Pageable);
}
