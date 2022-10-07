package ru.tagallteam.hackstarter.application.lvl.domain;

import lombok.Data;
import ru.tagallteam.hackstarter.application.achievement.domain.Achievement;
import ru.tagallteam.hackstarter.application.user.domain.User;

import javax.persistence.*;
import java.util.List;

@Data
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

    @OneToMany(mappedBy = "lvl")
    private List<Achievement> achievements;
}
