package ru.tagallteam.hackstarter.application.activity.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tagallteam.hackstarter.application.activity.model.ActivityDto;
import ru.tagallteam.hackstarter.application.activity.service.ActivityService;
import ru.tagallteam.hackstarter.application.common.CommonFilter;
import ru.tagallteam.hackstarter.application.common.Endpoints;
import ru.tagallteam.hackstarter.application.common.Page;
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
        return Page.empty();
    }

    @ApiOperation(value = "Получение активностей пользователя",
            notes = "Получение активностей пользователя по фильтру.")
    @SystemError
    @PostMapping(Endpoints.ActivityService.USER_ACTIVITIES)
    public Page<ActivityDto> userActivities(@RequestBody CommonFilter commonFilter, @PathVariable Long userId) {
        return Page.empty();
    }
}
