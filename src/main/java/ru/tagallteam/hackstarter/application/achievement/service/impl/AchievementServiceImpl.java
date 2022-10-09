package ru.tagallteam.hackstarter.application.achievement.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ru.tagallteam.hackstarter.application.achievement.domain.Achievement;
import ru.tagallteam.hackstarter.application.achievement.domain.AchievementAttribute;
import ru.tagallteam.hackstarter.application.achievement.domain.AchievementAttributeRepository;
import ru.tagallteam.hackstarter.application.achievement.domain.AchievementRepository;
import ru.tagallteam.hackstarter.application.achievement.domain.AchievementUser;
import ru.tagallteam.hackstarter.application.achievement.domain.AchievementUserPk;
import ru.tagallteam.hackstarter.application.achievement.domain.AchievementUserRepository;
import ru.tagallteam.hackstarter.application.achievement.mapper.AchievementMapper;
import ru.tagallteam.hackstarter.application.achievement.model.AchievementDto;
import ru.tagallteam.hackstarter.application.achievement.service.AchievementService;
import ru.tagallteam.hackstarter.application.activity.model.ActivityType;
import ru.tagallteam.hackstarter.application.activity.service.ActivityService;
import ru.tagallteam.hackstarter.application.event.modal.EventAttributeType;
import ru.tagallteam.hackstarter.application.lvl.domain.Lvl;
import ru.tagallteam.hackstarter.application.lvl.domain.LvlRepository;
import ru.tagallteam.hackstarter.application.user.domain.User;
import ru.tagallteam.hackstarter.application.user.domain.UserRepository;
import ru.tagallteam.hackstarter.errors.ErrorDescriptor;
import ru.tagallteam.hackstarter.integration.modal.SendCurrency;
import ru.tagallteam.hackstarter.integration.service.VtbIntegration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;
    private final VtbIntegration vtbIntegration;
    private final AchievementUserRepository achievementUserRepository;
    private final AchievementAttributeRepository achievementAttributeRepository;
    private final UserRepository userRepository;
    private final AchievementMapper achievementMapper;
    private final LvlRepository lvlRepository;
    private final ActivityService activityService;

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

        AchievementUserPk achievementUserPk = new AchievementUserPk();
        achievementUserPk.setAchievementId(achievement.getId());
        achievementUserPk.setUserId(user.getId());
        achievementUser.setAchievementUserPk(achievementUserPk);
        List<AchievementUser> userAchieve = new ArrayList<>(!CollectionUtils.isEmpty(user.getAchievements())
                ? user.getAchievements()
                : Collections.emptyList());

        userAchieve.add(achievementUser);
        user.setAchievements(userAchieve);
        userRepository.save(user);


        List<AchievementAttribute> achievementAttributes = achievementAttributeRepository.findAchievementAttributesByAchievement(achievement);
        String type = "";
        long number = 0;
        for (AchievementAttribute attribute : achievementAttributes) {
            if (Objects.equals(attribute.getType().getName(), EventAttributeType.NUMBER_AWARDS.getName())) {
                number = Long.parseLong(attribute.getValue());
            } else if (Objects.equals(attribute.getType().getName(), EventAttributeType.REWARD_TYPE.getName())) {
                type = attribute.getValue();
            }
        }
        if (type.equals("XP")) {
            User userXp = userRepository.findById(userId)
                    .orElseThrow(ErrorDescriptor.USER_NOT_FOUND::applicationException);
            Long newXp = userXp.getXp() + number;
            long currentLevel = Long.parseLong(userXp.getLvl().getName());
            userXp.setXp(newXp);
            Lvl nextLevel = lvlRepository.findLvlByName(String.valueOf((currentLevel + 1)))
                    .orElseThrow(ErrorDescriptor.NOT_FOUND::applicationException);
            if (newXp > nextLevel.getXp()) {
                userXp.setLvl(nextLevel);
                addNewLevelAchievements(userXp.getId(), nextLevel);
            }
            userRepository.save(userXp);
        } else if (type.equals("DR")) {
            vtbIntegration.sendRuble(SendCurrency.builder()
                    .amount((float) number)
                    .fromPrivateKey(privateKey)
                    .toPublicKey(user.getPublicKey())
                    .build());
        }

        activityService.addActivityToUser(userId, ActivityType.ACHIEVEMENT, achievement.getId());

    }

    private void addNewLevelAchievements(Long userId, Lvl lvl) {
        val achievements = achievementRepository.findAchievementsByLvl(lvl);
        for (Achievement achievement : achievements) {
            addAchievementToUser(userId, achievement.getId());
        }
    }
}
