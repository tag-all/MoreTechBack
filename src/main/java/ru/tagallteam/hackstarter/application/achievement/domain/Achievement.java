package ru.tagallteam.hackstarter.application.achievement.domain;

import java.util.List;
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

    @OneToMany(mappedBy = "achievement", cascade = CascadeType.ALL)
    private List<AchievementUser> requiredResourceList;


}
