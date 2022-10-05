package ru.tagallteam.hackstarter.application.auth.service;

import ru.tagallteam.hackstarter.application.auth.model.AuthDto;
import ru.tagallteam.hackstarter.application.auth.model.RegistrationDto;
import ru.tagallteam.hackstarter.application.auth.model.TokenDto;

public interface AuthService {
    TokenDto authorization(AuthDto authDto);

    TokenDto registration(RegistrationDto registrationDto);

    void logout(String accessToken);

    TokenDto updateToken(String accessToken);

}
