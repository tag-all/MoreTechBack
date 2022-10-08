package ru.tagallteam.hackstarter.application.activity.mapper;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import ru.tagallteam.hackstarter.application.activity.domain.Activity;
import ru.tagallteam.hackstarter.application.activity.model.ActivityDto;
import ru.tagallteam.hackstarter.application.activity.model.ActivityType;

@Component
public class ActivityMapper {

    public ActivityDto convertToActivityDto(Activity activity) {
        ActivityDto activityDto = new ActivityDto();
        activityDto.setId(activity.getId());
        activityDto.setType(!ObjectUtils.isEmpty(activity.getType()) ? ActivityType.valueOf(activity.getType()) : null);
        activityDto.setActivityDate(activity.getActivityDate());
        activityDto.setUserId(activity.getUser().getId());
        activityDto.setActivityId(activity.getActivityLinkId());
        return activityDto;
    }
}
