package ru.tagallteam.hackstarter.application.achievement.mapper;

import org.springframework.stereotype.Component;
import ru.tagallteam.hackstarter.application.achievement.domain.Achievement;
import ru.tagallteam.hackstarter.application.achievement.model.AchievementDto;

@Component
public class AchievementMapper {

    public AchievementDto convertToAchievementDto(Achievement achievement){
        AchievementDto achievementDto = new AchievementDto();
        achievementDto.setId(achievement.getId());
        achievementDto.setName(achievement.getName());
        achievementDto.setFileId(achievementDto.getFileId());
        return achievementDto;
    }
}
