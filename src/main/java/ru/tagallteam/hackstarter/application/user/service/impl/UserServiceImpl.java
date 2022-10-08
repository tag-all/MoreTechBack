package ru.tagallteam.hackstarter.application.user.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import ru.tagallteam.hackstarter.application.achievement.mapper.AchievementMapper;
import ru.tagallteam.hackstarter.application.achievement.model.AchievementDto;
import ru.tagallteam.hackstarter.application.activity.mapper.ActivityMapper;
import ru.tagallteam.hackstarter.application.common.filter.CommonFilter;
import ru.tagallteam.hackstarter.application.common.filter.Page;
import ru.tagallteam.hackstarter.application.event.mapper.EventMapper;
import ru.tagallteam.hackstarter.application.event.modal.EventDto;
import ru.tagallteam.hackstarter.application.user.domain.User;
import ru.tagallteam.hackstarter.application.user.domain.UserRepository;
import ru.tagallteam.hackstarter.application.user.mapper.UserMapper;
import ru.tagallteam.hackstarter.application.user.model.ProfileDto;
import ru.tagallteam.hackstarter.application.user.service.UserService;
import ru.tagallteam.hackstarter.errors.ErrorDescriptor;

import java.util.Comparator;
import java.util.List;
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
        profile.setActivities(user.getActivities()
                .stream()
                .map(activityMapper::convertToActivityDto)
                .collect(Collectors.toList()));
        List<EventDto> events = user.getEventsOfUser()
                .stream()
                .sorted(Comparator.comparing((item) -> item.getEvent().getEventTime()))
                .limit(4).map(item -> eventMapper.convertToEventDto(item.getEvent()))
                .collect(Collectors.toList());
        profile.setEvents(events);
        return profile;
    }

    @Override
    public ProfileDto userProfile(Long userId) {
        ErrorDescriptor.USER_NOT_FOUND.throwIsFalse(userRepository.existsById(userId));
        User user = userRepository.getReferenceById(userId);
        ProfileDto profile = userMapper.convertToUserDtoPublic(user);
        profile.setAchievements(user.getAchievements().stream()
                .map((item) -> {
                    AchievementDto achievementDto = achievementMapper.convertToAchievementDto(item.getAchievement());
                    achievementDto.setDate(item.getAchievementDate());
                    return achievementDto;
                })
                .collect(Collectors.toList()));
        profile.setActivities(user.getActivities()
                .stream()
                .map(activityMapper::convertToActivityDto)
                .collect(Collectors.toList()));
        List<EventDto> events = user.getEventsOfUser()
                .stream()
                .sorted(Comparator.comparing((item) -> item.getEvent().getEventTime()))
                .limit(4).map(item -> eventMapper.convertToEventDto(item.getEvent()))
                .toList();
        profile.setEvents(events);
        return profile;
    }

    @Override
    public Page<ProfileDto> userProfiles(CommonFilter filter) {
        Pageable pageable = PageRequest.of(filter.getPage() - 1, filter.getLimit(), Sort.by("xp").descending());
        if (ObjectUtils.isEmpty(filter.getName())) {
            val profiles = userRepository.findAll(pageable)
                    .map(userMapper::convertToUserDtoPublic);
            return Page.of(profiles);
        } else {
            val profiles = userRepository.findUsersByNameContains(filter.getName(), pageable)
                    .map(userMapper::convertToUserDtoPublic);
            return Page.of(profiles);
        }
    }
}
