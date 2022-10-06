package ru.tagallteam.hackstarter.application.activity.service;

import ru.tagallteam.hackstarter.application.activity.model.ActivityDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ActivityService {

    List<ActivityDto> getActivitiesByUser(Long userId);
    List<ActivityDto> getActivitiesBeforeDate(LocalDateTime dateTime);

}
