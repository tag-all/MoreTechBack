package ru.tagallteam.hackstarter.application.event.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class EventUserPk implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "event_id")
    private Long eventId;

}
