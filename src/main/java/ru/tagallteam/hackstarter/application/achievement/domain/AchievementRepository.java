package ru.tagallteam.hackstarter.application.achievement.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tagallteam.hackstarter.application.lvl.domain.Lvl;

import java.util.List;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {

    List<Achievement> findAchievementsByLvl(Lvl lvl);
}
