package ru.tagallteam.hackstarter.application.product.domain;


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
import ru.tagallteam.hackstarter.application.file.domain.File;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", allocationSize = 1, sequenceName = "nft_seq")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;

    @Column(name = "purchased")
    private Long purchased;

    @Column(name = "number_of_products")
    private Long number_of_products;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "file_id", nullable = false)
    private File file;

}
