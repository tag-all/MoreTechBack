package ru.tagallteam.hackstarter.application.common;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class CommonFilter extends PageableRequest {
    @Schema(description = "Сортировка по полю")
    private Sort sort;

    @Schema(description = "Фильтры по определенному полю. Бдует браться последний фильтр для поля")
    private List<Filter> filters;
}
