package ru.tagallteam.hackstarter.application.common.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonFilter extends PageableRequest {

    //Сортировка по имени
    private String name;

}
