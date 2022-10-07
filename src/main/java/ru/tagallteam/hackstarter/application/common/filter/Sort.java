package ru.tagallteam.hackstarter.application.common.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Sort {
    @Schema(description = "Тип сортировик")
    private SortType sortType;
    @Schema(description = "Наименование поля модели для сортировки")
    private String name;
}
