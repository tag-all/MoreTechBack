package ru.tagallteam.hackstarter.application.auth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AuthDto {
    @Schema(description = "Login пользователя")
    private String login;
    @Schema(description = "Пароль пользователя")
    private String password;
}
