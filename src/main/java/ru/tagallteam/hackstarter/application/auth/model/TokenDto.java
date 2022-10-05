package ru.tagallteam.hackstarter.application.auth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TokenDto {
    @Schema(description = "Токен доступа к приложению")
    private String authToken;

    @Schema(description = "Токен для обновления доступа")
    private String accessToken;
}
