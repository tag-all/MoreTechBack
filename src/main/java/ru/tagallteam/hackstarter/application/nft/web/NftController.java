package ru.tagallteam.hackstarter.application.nft.web;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.tagallteam.hackstarter.application.common.Endpoints;
import ru.tagallteam.hackstarter.application.nft.model.NftDto;
import ru.tagallteam.hackstarter.application.nft.model.NftUserDto;
import ru.tagallteam.hackstarter.application.nft.service.NftService;
import ru.tagallteam.hackstarter.errors.annotations.SystemError;

@Api(tags = "Работа с NFT")
@RestController
@RequiredArgsConstructor
public class NftController {
    private final NftService nftService;

    @ApiOperation(value = "Получение списка NFT",
            notes = "Получение списка NFT по ИД пользователя")
    @SystemError
    @GetMapping(Endpoints.NftService.USER_NFT)
    public List<NftDto> nftUser(@PathVariable Long userId){
        return nftService.nftUser(userId);
    }

    @ApiOperation(value = "Получение списка NFT с информацией о пользователе",
            notes = "Получение списка NFT с информацией о пользователе по ИД пользователя")
    @SystemError
    @GetMapping(Endpoints.NftService.NFT_INFO_WITH_USER)
    public NftUserDto nftWithUser(@PathVariable Long tokenId){
        return nftService.nftWithUser(tokenId);
    }
}
