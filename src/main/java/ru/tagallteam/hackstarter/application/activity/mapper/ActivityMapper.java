package ru.tagallteam.hackstarter.application.activity.mapper;

import org.springframework.stereotype.Component;
import ru.tagallteam.hackstarter.application.activity.domain.Activity;
import ru.tagallteam.hackstarter.application.activity.model.ActivityType;
import ru.tagallteam.hackstarter.application.activity.model.ActivityDto;

@Component
public class ActivityMapper {

    public ActivityDto convertToActivityDto(Activity activity){
        ActivityDto activityDto = new ActivityDto();
        activityDto.setId(activity.getId());
        activityDto.setType(activity.getType());
        activityDto.setActivityDate(activity.getActivityDate());
        activityDto.setUserId(activity.getUser().getId());
        activityDto.setActivityId(activity.getActivityId());
        return activityDto;
    }
}
