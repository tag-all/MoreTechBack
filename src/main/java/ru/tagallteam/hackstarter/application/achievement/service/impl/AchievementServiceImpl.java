package ru.tagallteam.hackstarter.application.achievement.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tagallteam.hackstarter.application.achievement.domain.Achievement;
import ru.tagallteam.hackstarter.application.achievement.domain.AchievementAttribute;
import ru.tagallteam.hackstarter.application.achievement.domain.AchievementAttributeRepository;
import ru.tagallteam.hackstarter.application.achievement.domain.AchievementRepository;
import ru.tagallteam.hackstarter.application.achievement.domain.AchievementUser;
import ru.tagallteam.hackstarter.application.achievement.domain.AchievementUserRepository;
import ru.tagallteam.hackstarter.application.achievement.mapper.AchievementMapper;
import ru.tagallteam.hackstarter.application.achievement.model.AchievementDto;
import ru.tagallteam.hackstarter.application.achievement.service.AchievementService;
import ru.tagallteam.hackstarter.application.event.modal.EventAttributeType;
import ru.tagallteam.hackstarter.application.user.domain.User;
import ru.tagallteam.hackstarter.application.user.domain.UserRepository;
import ru.tagallteam.hackstarter.errors.ErrorDescriptor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;
    private final AchievementUserRepository achievementUserRepository;
    private final AchievementAttributeRepository achievementAttributeRepository;
    private final UserRepository userRepository;
    private final AchievementMapper achievementMapper;

    @Value("${system-valet.private-key}")
    private String privateKey;

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

    @Override
    @Transactional
    public void addAchievementToUser(Long userId, Long achievementId) {
        User user = userRepository.findById(userId)
                .orElseThrow(ErrorDescriptor.USER_NOT_FOUND::applicationException);
        Achievement achievement = achievementRepository.findById(achievementId)
                .orElseThrow(ErrorDescriptor.NOT_FOUND::applicationException);

        AchievementUser achievementUser = new AchievementUser();
        achievementUser.setAchievement(achievement);
        achievementUser.setUser(user);
        achievementUser.setAchievementDate(LocalDateTime.now());
        achievementUserRepository.save(achievementUser);

        List<AchievementAttribute> achievementAttributes = achievementAttributeRepository.findAchievementAttributesByAchievement(achievement);

        String type = "";
        long number;
        for(AchievementAttribute attribute: achievementAttributes){
            if(Objects.equals(attribute.getType().getName(), EventAttributeType.NUMBER_AWARDS.getName())){
                number = Long.parseLong(attribute.getValue());
            }
            else if(Objects.equals(attribute.getType().getName(), EventAttributeType.REWARD_TYPE.getName())){
                type = attribute.getValue();
            }
        }
        if(type.equals("XP")){

        }
    }

}
