package ru.tagallteam.hackstarter.application.activity.web;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.tagallteam.hackstarter.application.activity.service.ActivityService;

@Api(tags = "Работа с активностями")
@RestController
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

}
