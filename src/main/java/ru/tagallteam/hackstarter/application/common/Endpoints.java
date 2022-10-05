package ru.tagallteam.hackstarter.application.common;

public interface Endpoints {
    interface AuthService{
        String LOGIN = "/login";
        String REGISTRATION = "/registration";
        String LOGOUT = "/logout";
        String UPDATE_TOKEN = "/token/update";
    }

    interface UserService{
        String PROFILE = "/profile";
    }
}
