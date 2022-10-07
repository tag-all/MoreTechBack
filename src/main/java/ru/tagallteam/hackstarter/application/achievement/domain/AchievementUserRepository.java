package ru.tagallteam.hackstarter.application.achievement.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tagallteam.hackstarter.application.user.domain.User;

import java.util.List;

public interface AchievementUserRepository extends JpaRepository<AchievementUser, Long> {

    List<AchievementUser> findAchievementUsersByUser(User user);
}
