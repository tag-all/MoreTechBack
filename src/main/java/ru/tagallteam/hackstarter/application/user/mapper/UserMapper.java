package ru.tagallteam.hackstarter.application.user.mapper;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import ru.tagallteam.hackstarter.application.user.domain.User;
import ru.tagallteam.hackstarter.application.user.domain.UserRole;
import ru.tagallteam.hackstarter.application.user.model.ProfileDto;

@Component
public class UserMapper {

    public ProfileDto convertToUserDto(User user) {
        ProfileDto profileDto = new ProfileDto();
        profileDto.setName(user.getName());
        profileDto.setLastName(user.getLastName());
        profileDto.setEmail(user.getEmail());
        profileDto.setLvl(user.getLvl().getId());
        profileDto.setEx(user.getXp());
        profileDto.setBalance(user.getBalance());
        profileDto.setRole(!ObjectUtils.isEmpty(user.getRole()) ? UserRole.valueOf(user.getRole()) : null);
        return profileDto;
    }

    public ProfileDto convertToUserDtoPublic(User user) {
        ProfileDto profileDto = new ProfileDto();
        profileDto.setName(user.getName());
        profileDto.setLastName(user.getLastName());
        profileDto.setEmail(user.getEmail());
        profileDto.setLvl(user.getLvl().getId());
        profileDto.setEx(user.getXp());
        profileDto.setRole(!ObjectUtils.isEmpty(user.getRole()) ? UserRole.valueOf(user.getRole()) : null);
        return profileDto;
    }
}
