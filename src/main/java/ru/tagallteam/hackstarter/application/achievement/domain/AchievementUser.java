package ru.tagallteam.hackstarter.application.achievement.domain;

import lombok.Data;
import ru.tagallteam.hackstarter.application.user.domain.User;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Embeddable
@Data
@Entity
@Table(name = "achievement_user")
public class AchievementUser {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "achievement_id")
    private Achievement achievement;

    @Column(name = "achievement_date")
    private LocalDateTime achievementDate;

}
