package ru.tagallteam.hackstarter.application.achievement.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AchievementAttributeRepository extends JpaRepository<AchievementAttribute, Long> {

    List<AchievementAttribute> findAchievementAttributesByAchievement(Achievement achievement);
}
