package ru.tagallteam.hackstarter.application.auth.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.tagallteam.hackstarter.application.auth.model.AuthDto;
import ru.tagallteam.hackstarter.application.auth.model.RegistrationDto;
import ru.tagallteam.hackstarter.application.auth.model.TokenDto;
import ru.tagallteam.hackstarter.application.auth.service.AuthService;
import ru.tagallteam.hackstarter.application.common.Endpoints;
import ru.tagallteam.hackstarter.errors.annotations.SystemError;

@Api(tags = "Авторизация")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @ApiOperation(value = "Авторизация пользователя",
            notes = "Авторизация по логину и паролю")
    @SystemError
    @PostMapping(Endpoints.AuthService.LOGIN)
    public TokenDto authorization(@RequestBody AuthDto authDto) {
        return authService.authorization(authDto);
    }

    @ApiOperation(value = "Регистрация прользователя",
            notes = "Регистрация пользователя")
    @SystemError
    @PostMapping(Endpoints.AuthService.REGISTRATION)
    public TokenDto registration(@RequestBody RegistrationDto registrationDto) {
        return authService.registration(registrationDto);
    }

    @ApiOperation(value = "Выход из приложения",
            notes = "Выход из приложения")
    @SystemError
    @PostMapping(Endpoints.AuthService.LOGOUT)
    public void logout(@RequestBody TokenDto tokenDto) {
        authService.logout(tokenDto.getAccessToken());
    }

    @ApiOperation(value = "Обновления токена",
            notes = "Обновление временного токена, который отвечает за авторизацию")
    @SystemError
    @PutMapping(Endpoints.AuthService.UPDATE_TOKEN)
    public TokenDto updateToken(@RequestBody TokenDto tokenDto) {
        return authService.updateToken(tokenDto.getAccessToken());
    }
}
