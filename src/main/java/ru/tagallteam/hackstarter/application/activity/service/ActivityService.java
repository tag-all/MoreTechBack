package ru.tagallteam.hackstarter.application.activity.service;

import ru.tagallteam.hackstarter.application.activity.model.ActivityDto;
import ru.tagallteam.hackstarter.application.common.filter.CommonFilter;
import ru.tagallteam.hackstarter.application.common.filter.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface ActivityService {

    List<ActivityDto> getActivitiesByUser(Long userId);
    List<ActivityDto> getActivitiesBeforeDate(LocalDateTime dateTime);

    Page<ActivityDto> getActivitiesByFilter(CommonFilter filter);
    Page<ActivityDto> getUserActivitiesByFilter(CommonFilter filter, Long userId);


}
