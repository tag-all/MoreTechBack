package ru.tagallteam.hackstarter.application.event.domain;

import lombok.Data;
import lombok.ToString;
import ru.tagallteam.hackstarter.application.admin.domain.Admin;
import ru.tagallteam.hackstarter.application.user.domain.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@ToString(of = "id")
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", allocationSize = 1, sequenceName = "event_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "creater", nullable = false)
    private User creater;

    private Long reviewer;

    @Column(name = "reviewer_status")
    private Boolean reviewerStatus;

    @Column(name = "topic")
    private String topic;

    @Column(name = "time_of_event")
    private LocalDateTime eventTime;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<EventAttribute> eventAttributes;

    @ManyToMany
    @JoinTable(
            name = "event_user",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

}
