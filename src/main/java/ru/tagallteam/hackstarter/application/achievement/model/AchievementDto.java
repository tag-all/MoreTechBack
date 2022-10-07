package ru.tagallteam.hackstarter.application.achievement.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AchievementDto {

    private Long id;

    private Long fileId;

    private String name;

    private LocalDateTime date;
}
