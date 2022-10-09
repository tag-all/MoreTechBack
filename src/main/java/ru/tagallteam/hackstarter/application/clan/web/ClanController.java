package ru.tagallteam.hackstarter.application.clan.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tagallteam.hackstarter.application.clan.model.ClanDto;
import ru.tagallteam.hackstarter.application.clan.service.ClanService;
import ru.tagallteam.hackstarter.application.common.Endpoints;
import ru.tagallteam.hackstarter.errors.annotations.SystemError;

import java.util.List;

@Api(tags = "Работа с кланами")
@RestController
@RequiredArgsConstructor
public class ClanController {

    private final ClanService clanService;

    @ApiOperation(value = "Добавление пользователя в клан",
            notes = "Добавление нового пользователя в клан")
    @SystemError
    @PostMapping(Endpoints.ClanService.CLAN_NEW_USER)
    public void addUserToClan(@PathVariable Long clanId) {
        clanService.addUserToClan(clanId);
    }

    @ApiOperation(value = "Получение кланов",
            notes = "Получение всех кланов")
    @SystemError
    @GetMapping(Endpoints.ClanService.CLANS)
    public List<ClanDto> getAllClans() {
        return clanService.getAllClans();
    }

    @ApiOperation(value = "Получение кланов пользователя",
            notes = "Получение всех кланов для конкретного пользователя")
    @SystemError
    @GetMapping(Endpoints.ClanService.CLANS_USER)
    public List<ClanDto> getAllClansForUser(@PathVariable Long userId) {
        return clanService.getAllClansForUser(userId);
    }


    @ApiOperation(value = "Получение клана для NFT",
            notes = "Получение клана для конкретного NFT")
    @SystemError
    @GetMapping(Endpoints.ClanService.CLAN_NFT)
    public ClanDto getClanForNFT(@PathVariable Long nftId) {
        return clanService.getClanForNFT(nftId);
    }


}
