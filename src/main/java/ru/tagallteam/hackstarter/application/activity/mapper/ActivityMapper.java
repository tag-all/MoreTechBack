package ru.tagallteam.hackstarter.application.activity.mapper;

import ru.tagallteam.hackstarter.application.activity.domain.Activity;
import ru.tagallteam.hackstarter.application.activity.domain.ActivityType;
import ru.tagallteam.hackstarter.application.activity.model.ActivityDto;

public class ActivityMapper {

    public ActivityDto convertToActivityDto(Activity activity){
        ActivityDto activityDto = new ActivityDto();
        activityDto.setId(activity.getId());
        activityDto.setType(ActivityType.valueOf(activity.getType()));
        activityDto.setActivityDate(activity.getActivityDate());
        activityDto.setUserId(activity.getUser().getId());
        activityDto.setActivityId(activity.getActivityId());
        return activityDto;
    }
}
