package ru.tagallteam.hackstarter.application.auth.model;

import lombok.Data;

@Data
public class AuthDto {
    private String login;
    private String password;
}
