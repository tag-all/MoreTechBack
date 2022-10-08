package ru.tagallteam.hackstarter.integration.modal;

import lombok.Data;

@Data
public class FilterHistory {
    private Long page;
    private Long offset;
    private String sort;
}
