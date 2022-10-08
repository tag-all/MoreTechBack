package ru.tagallteam.hackstarter.application.transfer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import ru.tagallteam.hackstarter.application.user.domain.User;

@Getter
@Setter
@Entity
@Table(name = "transfer")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", allocationSize = 1, sequenceName = "transfer_seq")
    private Long id;

    @Column(name = "transfer_key")
    private String key;

    @ManyToOne
    @JoinColumn(name="user_send", nullable=false)
    private User userSend;

    @ManyToOne
    @JoinColumn(name="user_get", nullable=false)
    private User userGet;
}
