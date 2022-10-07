package ru.tagallteam.hackstarter.application.event.domain;

import javax.persistence.*;

import lombok.Data;
import lombok.ToString;
import ru.tagallteam.hackstarter.application.event.modal.EventAttributeType;

@Data
@Entity
@ToString(of = "id")
@Table(name = "event_attribute")
public class EventAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", allocationSize = 1, sequenceName = "event_attribute_seq")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EventAttributeType type;

    @Column(name = "value")
    private String value;

}
