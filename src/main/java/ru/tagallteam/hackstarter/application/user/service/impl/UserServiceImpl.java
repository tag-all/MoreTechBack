package ru.tagallteam.hackstarter.application.user.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.tagallteam.hackstarter.application.user.domain.User;
import ru.tagallteam.hackstarter.application.user.domain.UserRepository;
import ru.tagallteam.hackstarter.application.user.mapper.UserMapper;
import ru.tagallteam.hackstarter.application.user.model.UserDto;
import ru.tagallteam.hackstarter.application.user.service.UserService;
import ru.tagallteam.hackstarter.errors.ErrorDescriptor;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper mapper = new UserMapper();
    @Override
    public UserDto getUserProfile() {
        UserDto userDto = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.getUserByEmail(userDto.getEmail())
                .orElseThrow(ErrorDescriptor.USER_NOT_FOUND::throwApplication);
        return mapper.convertToUserDto(user);
    }
}
