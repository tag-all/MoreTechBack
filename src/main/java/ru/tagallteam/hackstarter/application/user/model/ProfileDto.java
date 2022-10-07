package ru.tagallteam.hackstarter.application.user.model;

import lombok.Data;
import ru.tagallteam.hackstarter.application.achievement.model.AchievementDto;
import ru.tagallteam.hackstarter.application.activity.model.ActivityDto;

import java.util.List;

@Data
public class ProfileDto {
    private String name;
    private String lastName;
    private Long lvl;
    private Long ex;
    private Long balance;
    private String email;
    private List<AchievementDto> achievements;
    private List<ActivityDto> activities;
}
