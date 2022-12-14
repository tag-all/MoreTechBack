package ru.tagallteam.hackstarter.application.activity.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.tagallteam.hackstarter.application.activity.model.ActivityDto;
import ru.tagallteam.hackstarter.application.activity.service.ActivityService;
import ru.tagallteam.hackstarter.application.common.Endpoints;
import ru.tagallteam.hackstarter.application.common.filter.CommonFilter;
import ru.tagallteam.hackstarter.application.common.filter.Page;
import ru.tagallteam.hackstarter.errors.annotations.SystemError;

@Api(tags = "Работа с активностями")
@RestController
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @ApiOperation(value = "Получение активностей",
            notes = "Получение активностей по фильтру.")
    @SystemError
    @PostMapping(Endpoints.ActivityService.ACTIVITIES)
    public Page<ActivityDto> activities(@RequestBody CommonFilter commonFilter) {
        return activityService.getActivitiesByFilter(commonFilter);
    }

    @ApiOperation(value = "Получение активностей пользователя",
            notes = "Получение активностей пользователя по фильтру.")
    @SystemError
    @PostMapping(Endpoints.ActivityService.USER_ACTIVITIES)
    public Page<ActivityDto> userActivities(@RequestBody CommonFilter commonFilter, @PathVariable Long userId) {
        return activityService.getUserActivitiesByFilter(commonFilter, userId);
    }
}
