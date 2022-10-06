package ru.tagallteam.hackstarter.application.user.domain;

import lombok.Data;
import ru.tagallteam.hackstarter.application.achievement.domain.AchievementUser;
import ru.tagallteam.hackstarter.application.auth.domain.Token;
import ru.tagallteam.hackstarter.application.event.domain.Event;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;
import ru.tagallteam.hackstarter.application.nft.domain.Nft;

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

    @Column(name = "lvl")
    private Integer lvl;

    @Column(name = "xp")
    private Integer xp;

    @Column(name = "balance")
    private Integer balance;

    @Column(name = "notification_status")
    private Boolean notificationStatus;

    @OneToMany(mappedBy = "user")
    private List<Token> token;

    @ManyToMany(mappedBy = "users")
    private List<Event> events;

    @OneToMany(mappedBy = "user")
    private List<Nft> nfts;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<AchievementUser> achievementsOfUser;

}
