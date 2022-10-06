package ru.tagallteam.hackstarter.application.activity.model;

import lombok.Data;
import ru.tagallteam.hackstarter.application.activity.domain.ActivityType;

import java.time.LocalDateTime;

@Data
public class ActivityDto {

    private Long id;

    private ActivityType type;

    private Long userId;

    private Long activityId;

    private LocalDateTime activityDate;

}
