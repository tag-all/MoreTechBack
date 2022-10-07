package ru.tagallteam.hackstarter.application.user.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tagallteam.hackstarter.application.common.filter.CommonFilter;
import ru.tagallteam.hackstarter.application.common.Endpoints;
import ru.tagallteam.hackstarter.application.common.filter.Page;
import ru.tagallteam.hackstarter.application.user.model.ProfileDto;
import ru.tagallteam.hackstarter.application.user.service.UserService;
import ru.tagallteam.hackstarter.errors.annotations.SystemError;

@Api(tags = "Работа с пользователем")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "Получение профиля",
            notes = "Получение профиля авторизованного пользователя")
    @SystemError
    @GetMapping(Endpoints.UserService.PROFILE)
    public ProfileDto getUserProfile(){
        return userService.getUserProfile();
    }

    @ApiOperation(value = "Получение профиля другого пользователя",
            notes = "Получение профиля пользователя по его id, получение пользователя происходит" +
                    "с ограниченным количеством полей")
    @SystemError
    @GetMapping(Endpoints.UserService.USER_PROFILE)
    public ProfileDto userProfile(@PathVariable Long userId){
        return userService.userProfile(userId);
    }

    @ApiOperation(value = "Получение профилей пользователей",
            notes = "Получение профилей пользователей ")
    @SystemError
    @PostMapping(Endpoints.UserService.USERS_PROFILES)
    public Page<ProfileDto> userProfiles(@RequestBody CommonFilter filter){
        return userService.userProfiles(filter);
    }

}
