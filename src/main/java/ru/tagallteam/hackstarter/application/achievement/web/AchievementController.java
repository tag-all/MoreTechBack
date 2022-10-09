package ru.tagallteam.hackstarter.application.achievement.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.tagallteam.hackstarter.application.achievement.model.AchievementDto;
import ru.tagallteam.hackstarter.application.achievement.service.AchievementService;
import ru.tagallteam.hackstarter.application.common.Endpoints;
import ru.tagallteam.hackstarter.errors.annotations.SystemError;

import java.util.List;

@Api(tags = "Работа с достижениями")
@RestController
@RequiredArgsConstructor
public class AchievementController {

    private final AchievementService achievementService;

    @ApiOperation(value = "Получение всех достижений",
            notes = "Получение всех доступных достижений системы")
    @SystemError
    @GetMapping(Endpoints.AchievementService.ACHIEVEMENTS)
    public List<AchievementDto> achievements() {
        return achievementService.getAllAchievements();
    }


    @ApiOperation(value = "Получение всех достижений, полученных пользователем",
            notes = "Получение достижений пользователя, которые он получил и которые может получить")
    @SystemError
    @GetMapping(Endpoints.AchievementService.ACHIEVEMENTS_USER_GET)
    public List<AchievementDto> achievementsUsersGet(@PathVariable Long userId) {
        return achievementService.getAllAchievementsGetByUser(userId);
    }

    @ApiOperation(value = "Получение всех достижений, НЕ полученных пользователем",
            notes = "Получение достижений пользователя, которые он получил и которые может получить")
    @SystemError
    @GetMapping(Endpoints.AchievementService.ACHIEVEMENTS_USER_NOT_GET)
    public List<AchievementDto> achievementsUsers(@PathVariable Long userId) {
        return achievementService.getAllAchievementsNotGetByUser(userId);
    }
}
