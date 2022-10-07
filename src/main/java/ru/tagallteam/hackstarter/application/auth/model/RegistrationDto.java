package ru.tagallteam.hackstarter.application.auth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RegistrationDto {
    @Schema(description = "Email пользователя")
    private String email;

    @Schema(description = "Имя пользователя")
    private String name;

    @Schema(description = "Фамилия пользователя")
    private String lastName;

    @Schema(description = "Пароль пользователя")
    private String password;

    @Schema(description = "Состояние подписки на рассылку системы")
    private Boolean notificationFlag;
}
