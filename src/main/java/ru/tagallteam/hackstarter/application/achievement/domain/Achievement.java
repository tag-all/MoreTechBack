package ru.tagallteam.hackstarter.application.achievement.domain;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Set;

@Data
@Entity
@Table(name = "achievement")
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", allocationSize = 1, sequenceName = "customer_seq")
    private Long id;

    @Column(name = "file_uuid")
    private Integer fileId;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "achievement", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<AchievementUser> usersWithAchievement;

}
