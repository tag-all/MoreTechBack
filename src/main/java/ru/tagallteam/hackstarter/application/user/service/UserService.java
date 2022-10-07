package ru.tagallteam.hackstarter.application.user.service;

import ru.tagallteam.hackstarter.application.common.filter.CommonFilter;
import ru.tagallteam.hackstarter.application.common.filter.Page;
import ru.tagallteam.hackstarter.application.user.model.ProfileDto;

public interface UserService {
    ProfileDto getUserProfile();

    ProfileDto userProfile(Long userId);

    Page<ProfileDto> userProfiles(CommonFilter filter);

}
