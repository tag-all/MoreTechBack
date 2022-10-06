package ru.tagallteam.hackstarter.application.achievement.mapper;

import ru.tagallteam.hackstarter.application.achievement.domain.Achievement;
import ru.tagallteam.hackstarter.application.achievement.model.AchievementDto;
import ru.tagallteam.hackstarter.application.activity.domain.Activity;
import ru.tagallteam.hackstarter.application.activity.domain.ActivityType;
import ru.tagallteam.hackstarter.application.activity.model.ActivityDto;

public class AchievementMapper {

    public AchievementDto convertToAchievementDto(Achievement achievement){
        AchievementDto achievementDto = new AchievementDto();
        achievementDto.setId(achievement.getId());
        achievementDto.setName(achievement.getName());
        achievementDto.setFileId(achievementDto.getFileId());
        return achievementDto;
    }
}
