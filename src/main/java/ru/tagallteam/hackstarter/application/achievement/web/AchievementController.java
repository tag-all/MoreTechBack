package ru.tagallteam.hackstarter.application.achievement.web;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tagallteam.hackstarter.application.achievement.model.AchievementDto;
import ru.tagallteam.hackstarter.application.common.filter.CommonFilter;
import ru.tagallteam.hackstarter.application.common.Endpoints;
import ru.tagallteam.hackstarter.application.common.filter.Page;
import ru.tagallteam.hackstarter.errors.annotations.SystemError;

@RestController
@RequiredArgsConstructor
public class AchievementController {

    @ApiOperation(value = "Получение всех достижений",
            notes = "Получение всех доступных достижений системы")
    @SystemError
    @PostMapping(Endpoints.AchievementService.ACHIEVEMENTS)
    public Page<AchievementDto> achievements(@RequestBody CommonFilter filter) {
        return Page.empty();
    }


    @ApiOperation(value = "Получение всех достижений с учетом остижений пользователя",
            notes = "Получение достижений пользователя, которые он получил и которые может получить")
    @SystemError
    @GetMapping(Endpoints.AchievementService.ACHIEVEMENTS_USER)
    public Page<AchievementDto> achievementsUsers(@PathVariable Long userId, @RequestBody CommonFilter filter) {
        return Page.empty();
    }
}
