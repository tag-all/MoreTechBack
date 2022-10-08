package ru.tagallteam.hackstarter.application.event.domain;

import lombok.Getter;
import lombok.Setter;
import ru.tagallteam.hackstarter.application.user.domain.User;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "event_user")
public class EventUser {

    @EmbeddedId
    private EventUserPk eventUserPk;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("eventId")
    private Event event;

}
