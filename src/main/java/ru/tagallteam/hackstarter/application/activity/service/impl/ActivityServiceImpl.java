package ru.tagallteam.hackstarter.application.activity.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import ru.tagallteam.hackstarter.application.activity.domain.Activity;
import ru.tagallteam.hackstarter.application.activity.domain.ActivityRepository;
import ru.tagallteam.hackstarter.application.activity.mapper.ActivityMapper;
import ru.tagallteam.hackstarter.application.activity.model.ActivityDto;
import ru.tagallteam.hackstarter.application.activity.model.ActivityType;
import ru.tagallteam.hackstarter.application.activity.service.ActivityService;
import ru.tagallteam.hackstarter.application.common.filter.CommonFilter;
import ru.tagallteam.hackstarter.application.common.filter.Page;
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

    @Override
    public Page<ActivityDto> getActivitiesByFilter(CommonFilter filter) {
        Pageable pageable = getPageableForActivity(filter);
        if (ObjectUtils.isEmpty(filter.getName())) {
            val activities = activityRepository.findAll(pageable)
                    .map(mapper::convertToActivityDto);
            return Page.of(activities);
        } else {
            val activities = activityRepository.findAllByTypeContains(filter.getName(), pageable)
                    .map(mapper::convertToActivityDto);
            return Page.of(activities);
        }
    }

    @Override
    public Page<ActivityDto> getUserActivitiesByFilter(CommonFilter filter, Long userId) {
        Pageable pageable = getPageableForActivity(filter);
        if (ObjectUtils.isEmpty(filter.getName())) {
            val activities = activityRepository.findActivitiesByUser_Id(userId, pageable)
                    .map(mapper::convertToActivityDto);
            return Page.of(activities);
        } else {
            val activities = activityRepository.findAllByTypeContainsAndUser_Id(filter.getName(), userId, pageable)
                    .map(mapper::convertToActivityDto);
            return Page.of(activities);
        }
    }

    @Override
    public void addActivityToUser(Long userId, ActivityType activityType, Long activityId) {
        User user = userRepository.findById(userId)
                .orElseThrow(ErrorDescriptor.USER_NOT_FOUND::applicationException);

        Activity activity = new Activity();
        activity.setActivityDate(LocalDateTime.now());
        activity.setUser(user);
        activity.setType(activityType.name());
        activity.setActivityLinkId(activityId);
        activityRepository.save(activity);
    }


    private Pageable getPageableForActivity(CommonFilter filter) {
        return PageRequest.of(filter.getPage() - 1, filter.getLimit(), Sort.by("activityDate").descending());
    }

}

