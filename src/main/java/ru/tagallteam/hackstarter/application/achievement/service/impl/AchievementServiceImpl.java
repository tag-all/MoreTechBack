package ru.tagallteam.hackstarter.application.achievement.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.tagallteam.hackstarter.application.achievement.domain.AchievementRepository;
import ru.tagallteam.hackstarter.application.achievement.domain.AchievementUserRepository;
import ru.tagallteam.hackstarter.application.achievement.mapper.AchievementMapper;
import ru.tagallteam.hackstarter.application.achievement.model.AchievementDto;
import ru.tagallteam.hackstarter.application.achievement.service.AchievementService;
import ru.tagallteam.hackstarter.application.user.domain.User;
import ru.tagallteam.hackstarter.application.user.domain.UserRepository;
import ru.tagallteam.hackstarter.errors.ErrorDescriptor;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;
    private final AchievementUserRepository achievementUserRepository;
    private final UserRepository userRepository;
    private final AchievementMapper achievementMapper;

    @Override
    public List<AchievementDto> getAllAchievements() {
        return achievementRepository.findAll()
                .stream()
                .map(achievementMapper::convertToAchievementDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AchievementDto> getAllAchievementsGetByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(ErrorDescriptor.USER_NOT_FOUND::applicationException);
        return achievementUserRepository.findAchievementUsersByUser(user)
                .stream()
                .map((item) -> {
                    val dto = achievementMapper.convertToAchievementDto(item.getAchievement());
                    dto.setDate(item.getAchievementDate());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<AchievementDto> getAllAchievementsNotGetByUser(Long userId) {
        List<AchievementDto> userAchievements = getAllAchievementsGetByUser(userId);
        return achievementRepository.findAll()
                .stream()
                .map(achievementMapper::convertToAchievementDto)
                .filter(item -> !userAchievements.contains(item))
                .collect(Collectors.toList());
    }
}
