package ru.tagallteam.hackstarter.application.achievement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import ru.tagallteam.hackstarter.application.event.modal.EventAttributeType;

@Data
@Entity
@Table(name = "achievement_attribute")
public class AchievementAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", allocationSize = 1, sequenceName = "achievement_attribute_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "achievement_id")
    private Achievement achievement;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EventAttributeType type;

    @Column(name = "value")
    private String value;
}
