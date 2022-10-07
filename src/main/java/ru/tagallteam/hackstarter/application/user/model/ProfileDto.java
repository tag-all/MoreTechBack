package ru.tagallteam.hackstarter.application.user.model;

import lombok.Data;
import ru.tagallteam.hackstarter.application.achievement.model.AchievementDto;

import java.util.List;

@Data
public class ProfileDto {
    private String name;
    private String lastName;
    private Long age;
    private String email;
    private List<AchievementDto> achievements;
}
