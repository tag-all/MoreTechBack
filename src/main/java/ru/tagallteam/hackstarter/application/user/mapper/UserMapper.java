package ru.tagallteam.hackstarter.application.user.mapper;

import org.springframework.stereotype.Component;
import ru.tagallteam.hackstarter.application.user.domain.User;
import ru.tagallteam.hackstarter.application.user.model.UserDto;

@Component
public class UserMapper {

    public UserDto convertToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
