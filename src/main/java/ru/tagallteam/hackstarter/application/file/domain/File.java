package ru.tagallteam.hackstarter.application.file.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "file")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", allocationSize = 1, sequenceName = "file_seq")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "expansion")
    private String expansion;

    @Column(name = "data")
    private String data;

    @Column(name = "create_date")
    private LocalDateTime createDate;
}
