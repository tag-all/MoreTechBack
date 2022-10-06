package ru.tagallteam.hackstarter.application.event.domain;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;
import ru.tagallteam.hackstarter.application.admin.domain.Admin;
import ru.tagallteam.hackstarter.application.user.domain.User;

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
    private Admin admin;

    @Column(name = "topic")
    private String topic;

    @Column(name = "time_of_event")
    private LocalDateTime eventTime;

    @Column(name = "topic")
    private String description;

    @OneToMany(mappedBy = "event")
    private List<EventAttribute> eventAttributes;

    @ManyToMany
    @JoinTable(
            name = "event_user",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

}
