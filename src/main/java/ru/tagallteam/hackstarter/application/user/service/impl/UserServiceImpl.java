package ru.tagallteam.hackstarter.application.user.service.impl;

import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.tagallteam.hackstarter.application.achievement.mapper.AchievementMapper;
import ru.tagallteam.hackstarter.application.achievement.model.AchievementDto;
import ru.tagallteam.hackstarter.application.activity.mapper.ActivityMapper;
import ru.tagallteam.hackstarter.application.common.filter.CommonFilter;
import ru.tagallteam.hackstarter.application.common.filter.Page;
import ru.tagallteam.hackstarter.application.event.domain.Event;
import ru.tagallteam.hackstarter.application.event.mapper.EventMapper;
import ru.tagallteam.hackstarter.application.event.modal.EventDto;
import ru.tagallteam.hackstarter.application.user.domain.User;
import ru.tagallteam.hackstarter.application.user.domain.UserRepository;
import ru.tagallteam.hackstarter.application.user.mapper.UserMapper;
import ru.tagallteam.hackstarter.application.user.model.ProfileDto;
import ru.tagallteam.hackstarter.application.user.service.UserService;
import ru.tagallteam.hackstarter.errors.ErrorDescriptor;

import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;
    private final AchievementMapper achievementMapper;

    private final ActivityMapper activityMapper;

    private final EventMapper eventMapper;

    @Override
    public ProfileDto getUserProfile() {
        ProfileDto profileDto = (ProfileDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.getUserByEmail(profileDto.getEmail())
                .orElseThrow(ErrorDescriptor.USER_NOT_FOUND::applicationException);
        ProfileDto profile = userMapper.convertToUserDto(user);
        profile.setAchievements(user.getAchievements().stream()
                .map((item) -> {
                    AchievementDto achievementDto = achievementMapper.convertToAchievementDto(item.getAchievement());
                    achievementDto.setDate(item.getAchievementDate());
                    return achievementDto;
                })
                .collect(Collectors.toList()));
        profile.setActivities(user.getActivities().stream()
                .map(activityMapper::convertToActivityDto).collect(Collectors.toList()));
        List<EventDto> events = user.getEvents().stream().sorted(Comparator.comparing(Event::getEventTime))
                .limit(4).map(eventMapper::convertToEventDto).collect(Collectors.toList());
        profile.setEvents(events);
        return profile;
    }

    @Override
    public ProfileDto userProfile(Long userId) {
        ErrorDescriptor.USER_NOT_FOUND.throwIsFalse(userRepository.existsById(userId));
        User user = userRepository.getById(userId);
        ProfileDto profile = userMapper.convertToUserDtoPublic(user);
        profile.setAchievements(user.getAchievements().stream()
                .map((item) -> {
                    AchievementDto achievementDto = achievementMapper.convertToAchievementDto(item.getAchievement());
                    achievementDto.setDate(item.getAchievementDate());
                    return achievementDto;
                })
                .collect(Collectors.toList()));
        profile.setActivities(user.getActivities().stream()
                .map(activityMapper::convertToActivityDto).collect(Collectors.toList()));
        return profile;
    }

    @Override
    public Page<ProfileDto> userProfiles(CommonFilter filter) {
        return Page.empty();
    }
}
