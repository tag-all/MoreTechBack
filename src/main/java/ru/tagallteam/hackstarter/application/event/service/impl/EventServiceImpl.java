package ru.tagallteam.hackstarter.application.event.service.impl;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import ru.tagallteam.hackstarter.application.common.filter.CommonFilter;
import ru.tagallteam.hackstarter.application.common.filter.Page;
import ru.tagallteam.hackstarter.application.event.domain.Event;
import ru.tagallteam.hackstarter.application.event.domain.EventRepository;
import ru.tagallteam.hackstarter.application.event.mapper.EventMapper;
import ru.tagallteam.hackstarter.application.event.modal.EventDto;
import ru.tagallteam.hackstarter.application.event.service.EventService;
import ru.tagallteam.hackstarter.application.user.domain.User;
import ru.tagallteam.hackstarter.application.user.domain.UserRepository;
import ru.tagallteam.hackstarter.application.user.model.ProfileDto;
import ru.tagallteam.hackstarter.errors.ErrorDescriptor;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventMapper eventMapper;

    private final EventRepository eventRepository;

    private final UserRepository userRepository;

    @Override
    public Page<EventDto> events(CommonFilter filter) {
        Pageable pageable = getPageableForEvent(filter);
        if (ObjectUtils.isEmpty(filter.getName())) {
            val events = eventRepository.findAll(pageable)
                    .map(eventMapper::convertToEventDto);
            return Page.of(events);
        } else {
            val events = eventRepository.findEventsByTopicContains(filter.getName(), pageable)
                    .map(eventMapper::convertToEventDto);
            return Page.of(events);
        }
    }

    @Override
    public Page<EventDto> eventsUser(Long userId, CommonFilter filter) {
        Pageable pageable = getPageableForEvent(filter);
        if (ObjectUtils.isEmpty(filter.getName())) {
            val events = eventRepository.findUserEventsByUserId(userId, pageable)
                    .map(eventMapper::convertToEventDto);
            return Page.of(events);
        } else {
            val events = eventRepository.findUserEventsByUserIdWhichContains(userId, filter.getName(), pageable)
                    .map(eventMapper::convertToEventDto);
            return Page.of(events);
        }

    }

    @Override
    public void createEventsFeedback(EventDto event) {
        Event newEvent = eventMapper.convertToEvent(event);
        ProfileDto profileDto = (ProfileDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.getUserByEmail(profileDto.getEmail())
                .orElseThrow(ErrorDescriptor.USER_NOT_FOUND::applicationException);
        newEvent.setCreater(user);
        newEvent.setReviewerStatus(false);
        eventRepository.save(newEvent);
    }

    @Override
    public void signInForEvent(Long eventId) {
        ProfileDto profileDto = (ProfileDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.getUserByEmail(profileDto.getEmail())
                .orElseThrow(ErrorDescriptor.USER_NOT_FOUND::applicationException);
        Event event = eventRepository.getById(eventId);
        ErrorDescriptor.EVENT_NOT_PUBLIC.throwIsFalse(event.getReviewerStatus());
        List<User> users = event.getUsers();
        users.add(user);
        event.setUsers(users);
        eventRepository.save(event);
    }


    private Pageable getPageableForEvent(CommonFilter filter) {
        return PageRequest.of(filter.getPage() - 1, filter.getLimit(), Sort.by("time_of_event").descending());
    }

}
