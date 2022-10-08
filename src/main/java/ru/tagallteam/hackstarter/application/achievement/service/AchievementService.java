package ru.tagallteam.hackstarter.application.achievement.service;

import ru.tagallteam.hackstarter.application.achievement.model.AchievementDto;

import java.util.List;

public interface AchievementService {

    List<AchievementDto> getAllAchievements();
    List<AchievementDto> getAllAchievementsGetByUser(Long userId);
    List<AchievementDto> getAllAchievementsNotGetByUser(Long userId);
    void addAchievementToUser(Long userId, Long achievementId);
}
