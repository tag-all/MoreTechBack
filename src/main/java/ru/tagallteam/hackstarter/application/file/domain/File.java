package ru.tagallteam.hackstarter.application.file.domain;

import lombok.Getter;
import lombok.Setter;
import ru.tagallteam.hackstarter.application.achievement.domain.Achievement;
import ru.tagallteam.hackstarter.application.product.domain.Product;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "file")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", allocationSize = 1, sequenceName = "file_seq")
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "expansion")
    private String expansion;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "data")
    private String data;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "file")
    private List<Product> products;

    @OneToMany(mappedBy = "file", cascade = CascadeType.ALL)
    private List<Achievement> achievements;
}
