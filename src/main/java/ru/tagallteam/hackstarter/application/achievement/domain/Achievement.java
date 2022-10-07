package ru.tagallteam.hackstarter.application.achievement.domain;

import java.util.List;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

import ru.tagallteam.hackstarter.application.lvl.domain.Lvl;
import ru.tagallteam.hackstarter.application.user.domain.User;

@Data
@Entity
@Table(name = "achievement")
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", allocationSize = 1, sequenceName = "customer_seq")
    private Long id;

    @Column(name = "file_id")
    private Long fileId;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name="lvl_id", nullable=false)
    private Lvl lvl;

    @OneToMany(mappedBy = "achievement", cascade = CascadeType.ALL)
    private List<AchievementUser> requiredResourceList;


}
