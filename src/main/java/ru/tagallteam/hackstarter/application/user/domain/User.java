package ru.tagallteam.hackstarter.application.user.domain;

import lombok.Getter;
import lombok.Setter;
import ru.tagallteam.hackstarter.application.achievement.domain.AchievementUser;
import ru.tagallteam.hackstarter.application.activity.domain.Activity;
import ru.tagallteam.hackstarter.application.auth.domain.Token;
import ru.tagallteam.hackstarter.application.event.domain.Event;
import ru.tagallteam.hackstarter.application.event.domain.EventUser;
import ru.tagallteam.hackstarter.application.lvl.domain.Lvl;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", allocationSize = 1, sequenceName = "customer_seq")
    private Long id;

    @Column(name = "role")
    private String role;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<AchievementUser> achievements;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<EventUser> eventsOfUser;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Activity> activities;

    @OneToMany(mappedBy = "creater", cascade = CascadeType.ALL)
    private List<Event> createrEvents;

}
