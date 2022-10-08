package ru.tagallteam.hackstarter.application.lvl.domain;

import lombok.Getter;
import lombok.Setter;
import ru.tagallteam.hackstarter.application.achievement.domain.Achievement;
import ru.tagallteam.hackstarter.application.user.domain.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Lvl")
public class Lvl {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", allocationSize = 1, sequenceName = "lvl_seq")
    private Long id;

    @Column(name = "xp")
    private Long xp;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "lvl")
    private List<User> users;

    @OneToMany(mappedBy = "lvl", cascade = CascadeType.ALL)
    private List<Achievement> achievements;
}
