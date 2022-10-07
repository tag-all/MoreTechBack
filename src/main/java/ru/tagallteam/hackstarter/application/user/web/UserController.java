package ru.tagallteam.hackstarter.application.user.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tagallteam.hackstarter.application.common.Endpoints;
import ru.tagallteam.hackstarter.application.user.model.ProfileDto;
import ru.tagallteam.hackstarter.application.user.service.UserService;
import ru.tagallteam.hackstarter.errors.annotations.SystemError;

@Api(tags = "Работа с пользователем")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "Полученеи профиля",
            notes = "Получение профиля авторизованного пользователя")
    @SystemError
    @GetMapping(Endpoints.UserService.PROFILE)
    public ProfileDto getUserProfile(){
        return userService.getUserProfile();
    }

}
