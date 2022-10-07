package ru.tagallteam.hackstarter.application.achievement.domain;

import lombok.Getter;
import lombok.Setter;
import ru.tagallteam.hackstarter.application.user.domain.User;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "achievement_user")
public class AchievementUser {

    @EmbeddedId
    private AchievementUserPk achievementUserPk;

    @Column(name = "achievement_date")
    private LocalDateTime achievementDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("userId")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("achievementId")
    private Achievement achievement;

}
