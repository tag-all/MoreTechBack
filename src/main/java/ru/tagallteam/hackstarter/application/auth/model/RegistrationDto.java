package ru.tagallteam.hackstarter.application.auth.model;

import lombok.Data;

@Data
public class RegistrationDto {
    private String email;
    private String password;
}
