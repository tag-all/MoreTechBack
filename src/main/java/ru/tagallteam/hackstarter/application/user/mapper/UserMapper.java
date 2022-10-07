package ru.tagallteam.hackstarter.application.user.mapper;

import org.springframework.stereotype.Component;
import ru.tagallteam.hackstarter.application.user.domain.User;
import ru.tagallteam.hackstarter.application.user.model.ProfileDto;

@Component
public class UserMapper {

    public ProfileDto convertToUserDto(User user){
        ProfileDto profileDto = new ProfileDto();
        profileDto.setName(user.getName());
        profileDto.setLastName(user.getLastName());
        profileDto.setEmail(user.getEmail());
        return profileDto;
    }
}
