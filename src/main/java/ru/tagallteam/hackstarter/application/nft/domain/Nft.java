package ru.tagallteam.hackstarter.application.nft.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import ru.tagallteam.hackstarter.application.admin.domain.Admin;
import ru.tagallteam.hackstarter.application.file.domain.File;
import ru.tagallteam.hackstarter.application.user.domain.User;

@Data
@Entity
@Table(name = "nft")
public class Nft {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", allocationSize = 1, sequenceName = "nft_seq")
    private Long id;

    @Column(name = "certificate")
    private String certificate;

    @Column(name = "price")
    private Long price;

    @ManyToOne
    @JoinColumn(name = "creater", nullable = false)
    private Admin admin;

    @ManyToOne
    @JoinColumn(name = "owner", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "file_id", nullable = false)
    private File file;

}
