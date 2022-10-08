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
import ru.tagallteam.hackstarter.application.event.domain.Event;
import ru.tagallteam.hackstarter.application.event.mapper.EventMapper;
import ru.tagallteam.hackstarter.application.event.modal.EventDto;
import ru.tagallteam.hackstarter.application.lvl.domain.Lvl;
import ru.tagallteam.hackstarter.application.lvl.domain.LvlRepository;
import ru.tagallteam.hackstarter.application.transfer.service.TransferService;
import ru.tagallteam.hackstarter.application.user.domain.User;
import ru.tagallteam.hackstarter.application.user.domain.UserRepository;
import ru.tagallteam.hackstarter.application.user.mapper.UserMapper;
import ru.tagallteam.hackstarter.application.user.model.ProfileDto;
import ru.tagallteam.hackstarter.application.user.service.UserService;
import ru.tagallteam.hackstarter.errors.ErrorDescriptor;
import ru.tagallteam.hackstarter.integration.service.VtbIntegration;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final LvlRepository lvlRepository;

    private final UserMapper userMapper;
    private final AchievementMapper achievementMapper;

    private final ActivityMapper activityMapper;

    private final EventMapper eventMapper;

    private final VtbIntegration integration;

    private final TransferService transferService;

    @Override
    public ProfileDto getUserProfile() {
        ProfileDto profileDto = (ProfileDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.getUserByEmail(profileDto.getEmail())
                .orElseThrow(ErrorDescriptor.USER_NOT_FOUND::applicationException);

        ProfileDto profile = userMapper.convertToUserDto(user,
                integration.getBalanceWallet(user.getPublicKey()),
                integration.getNftBalanceWallet(user.getPublicKey()));
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
        List<EventDto> events = user.getEvents().stream().sorted(Comparator.comparing(Event::getEventTime))
                .limit(4).map(eventMapper::convertToEventDto).collect(Collectors.toList());
        profile.setEvents(events);
        profile.setTransfersFrom(user.getTransferSends().stream()
                .map(it -> transferService.transferModel(it.getKey())).toList());
        profile.setTransfersTo(user.getTransferGet().stream()
                .map(it -> transferService.transferModel(it.getKey())).toList());
        return profile;
    }

    @Override
    public ProfileDto userProfile(Long userId) {
        ErrorDescriptor.USER_NOT_FOUND.throwIsFalse(userRepository.existsById(userId));
        User user = userRepository.findById(userId)
                .orElseThrow(ErrorDescriptor.USER_NOT_FOUND::applicationException);
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
        List<EventDto> events = user.getEvents().stream().sorted(Comparator.comparing(Event::getEventTime))
                .limit(4).map(eventMapper::convertToEventDto).collect(Collectors.toList());
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

    @Override
    public void addXPtoUser(Long userId, Long XP) {
        User user = userRepository.findById(userId)
                .orElseThrow(ErrorDescriptor.USER_NOT_FOUND::applicationException);
        Long newXp = user.getXp() + XP;
        long currentLevel = Long.parseLong(user.getLvl().getName());

        user.setXp(newXp);
        Lvl nextLevel = lvlRepository.findLvlByName(String.valueOf((currentLevel + 1))).orElseThrow(ErrorDescriptor.NOT_FOUND::applicationException);
        if (newXp > nextLevel.getXp()) {
            user.setLvl(nextLevel);
        }
        userRepository.save(user);
    }
}
