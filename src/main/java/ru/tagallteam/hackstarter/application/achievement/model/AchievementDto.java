package ru.tagallteam.hackstarter.application.achievement.model;

import lombok.Data;
import ru.tagallteam.hackstarter.application.achievement.domain.AchievementUser;
import ru.tagallteam.hackstarter.application.activity.domain.ActivityType;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class AchievementDto {

    private Long id;

    private Long fileId;

    private String name;

    private LocalDateTime date;
}
