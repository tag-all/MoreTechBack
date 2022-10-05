package ru.tagallteam.hackstarter.application.user.model;

import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String lastName;
    private Long age;
    private String email;
}
