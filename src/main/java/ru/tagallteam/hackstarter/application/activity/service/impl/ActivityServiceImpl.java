package ru.tagallteam.hackstarter.application.activity.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tagallteam.hackstarter.application.activity.domain.ActivityRepository;
import ru.tagallteam.hackstarter.application.activity.mapper.ActivityMapper;
import ru.tagallteam.hackstarter.application.activity.model.ActivityDto;
import ru.tagallteam.hackstarter.application.activity.service.ActivityService;
import ru.tagallteam.hackstarter.application.user.domain.User;
import ru.tagallteam.hackstarter.application.user.domain.UserRepository;
import ru.tagallteam.hackstarter.errors.ErrorDescriptor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    private final ActivityMapper mapper = new ActivityMapper();

    @Override
    public List<ActivityDto> getActivitiesByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(ErrorDescriptor.USER_NOT_FOUND::applicationException);
        return activityRepository.findActivitiesByUser(user)
                .stream()
                .map(mapper::convertToActivityDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActivityDto> getActivitiesBeforeDate(LocalDateTime dateTime) {
        return activityRepository.findActivitiesByActivityDateBeforeOrderByActivityDateDesc(dateTime)
                .stream()
                .map(mapper::convertToActivityDto)
                .collect(Collectors.toList());
    }
}
