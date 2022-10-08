package ru.tagallteam.hackstarter.application.user.domain;

import javax.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import ru.tagallteam.hackstarter.application.achievement.domain.AchievementUser;
import ru.tagallteam.hackstarter.application.activity.domain.Activity;
import ru.tagallteam.hackstarter.application.auth.domain.Token;
import ru.tagallteam.hackstarter.application.clan.domain.Clan;
import ru.tagallteam.hackstarter.application.event.domain.Event;
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
import ru.tagallteam.hackstarter.application.nft.domain.Nft;
import ru.tagallteam.hackstarter.application.transfer.domain.Transfer;

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

    @Column(name = "xp")
    private Long xp;

    @Column(name = "notification_status")
    private Boolean notificationStatus;

    @Column(name = "private_key")
    private String privateKey;

    @Column(name = "public_key")
    private String publicKey;

    @ManyToOne
    @JoinColumn(name = "lvl_id", nullable = false)
    private Lvl lvl;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Token> token;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<AchievementUser> achievements;

    @ManyToMany(mappedBy = "users")
    private List<Event> events;

    @ManyToMany(mappedBy = "users")
    private List<Clan> clans;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Activity> activities;

    @OneToMany(mappedBy = "creater", cascade = CascadeType.ALL)
    private List<Event> createrEvents;

    @OneToMany(mappedBy = "userSend", cascade = CascadeType.ALL)
    private List<Transfer> transferSends;

    @OneToMany(mappedBy = "userGet", cascade = CascadeType.ALL)
    private List<Transfer> transferGet;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Nft> nfts;

}
