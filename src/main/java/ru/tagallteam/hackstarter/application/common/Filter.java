package ru.tagallteam.hackstarter.application.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Filter {
    @Schema(description = "Наименовние поля для фильтрации")
    private String name;
    @Schema(description = "Тип фильтрации")
    private FilterType type;
    @Schema(description = "Значение для сравнения")
    private String value;
}