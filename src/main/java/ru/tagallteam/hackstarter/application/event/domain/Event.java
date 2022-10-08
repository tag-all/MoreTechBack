package ru.tagallteam.hackstarter.application.event.domain;

import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import ru.tagallteam.hackstarter.application.user.domain.User;

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
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
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
