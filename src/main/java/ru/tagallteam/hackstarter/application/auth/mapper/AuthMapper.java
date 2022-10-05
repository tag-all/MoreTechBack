package ru.tagallteam.hackstarter.application.auth.mapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.tagallteam.hackstarter.application.auth.model.RegistrationDto;
import ru.tagallteam.hackstarter.application.user.domain.User;

public class AuthMapper {

    public User convertToUserRegistration(RegistrationDto registrationDto){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        return user;
    }
}
