package ru.tagallteam.hackstarter.application.event.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.tagallteam.hackstarter.application.common.CommonFilter;
import ru.tagallteam.hackstarter.application.common.Page;
import ru.tagallteam.hackstarter.application.event.domain.Event;
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

    private final UserRepository userRepository;

    @Override
    public Page<EventDto> events(CommonFilter filter) {
        return Page.empty();
    }

    @Override
    public Page<EventDto> eventsUser(Long userId, CommonFilter filter) {
        return Page.empty();
    }

    @Override
    public void createEventsFeedback(EventDto event) {
        Event newEvent = eventMapper.convertToEvent(event);
        ProfileDto profileDto = (ProfileDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.getUserByEmail(profileDto.getEmail())
                .orElseThrow(ErrorDescriptor.USER_NOT_FOUND::throwApplication);
        newEvent.setCreater(user);
        newEvent.setReviewerStatus(false);
    }
}
