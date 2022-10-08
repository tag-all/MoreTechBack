package ru.tagallteam.hackstarter.application.achievement.domain;

import lombok.Getter;
import lombok.Setter;
import ru.tagallteam.hackstarter.application.file.domain.File;
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
@Table(name = "achievement")
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", allocationSize = 1, sequenceName = "customer_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "file_id", nullable = false)
    private File file;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "lvl_id", nullable = false)
    private Lvl lvl;

    @OneToMany(mappedBy = "achievement", cascade = CascadeType.ALL)
    private List<AchievementAttribute> achievementAttributes;

    @OneToMany(mappedBy = "achievement", cascade = CascadeType.ALL)
    private List<AchievementUser> requiredResourceList;

}
