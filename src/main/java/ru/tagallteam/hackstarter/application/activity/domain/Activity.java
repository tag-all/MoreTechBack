package ru.tagallteam.hackstarter.application.activity.domain;

import lombok.Data;
import ru.tagallteam.hackstarter.application.activity.model.ActivityType;
import ru.tagallteam.hackstarter.application.user.domain.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", allocationSize = 1, sequenceName = "customer_seq")
    private Long id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ActivityType type;

    @Column(name = "activity_link_id")
    private Long activityId;

    @Column(name = "activity_date")
    private LocalDateTime activityDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
