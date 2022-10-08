package ru.tagallteam.hackstarter.application.activity.domain;

import lombok.Getter;
import lombok.Setter;
import ru.tagallteam.hackstarter.application.user.domain.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", allocationSize = 1, sequenceName = "activity_seq")
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "activity_link_id")
    private Long activityLinkId;

    @Column(name = "activity_date")
    private LocalDateTime activityDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
