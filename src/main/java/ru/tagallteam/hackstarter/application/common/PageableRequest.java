package ru.tagallteam.hackstarter.application.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PageableRequest {

    @Schema(description = "Максимальное количество отображаемых элементов")
    private Integer limit = 20;

    @Schema(description = "Количество страниц")
    private Integer page = 1;

}
