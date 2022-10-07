package ru.tagallteam.hackstarter.application.user.domain;

import lombok.Data;
import ru.tagallteam.hackstarter.application.achievement.domain.AchievementUser;
import ru.tagallteam.hackstarter.application.activity.domain.Activity;
import ru.tagallteam.hackstarter.application.auth.domain.Token;
import ru.tagallteam.hackstarter.application.event.domain.Event;
import ru.tagallteam.hackstarter.application.lvl.domain.Lvl;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "customer")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", allocationSize = 1, sequenceName = "customer_seq")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "lvl_id", nullable = false)
    private Lvl lvl;

    @Column(name = "xp")
    private Long xp;

    @Column(name = "balance")
    private Long balance;

    @Column(name = "notification_status")
    private Boolean notificationStatus;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Token> token;

    @ManyToMany(mappedBy = "users")
    private List<Event> events;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<AchievementUser> achievements;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Activity> activities;

    @OneToMany(mappedBy = "creater", cascade = CascadeType.ALL)
    private List<Event> createrEvents;

}
