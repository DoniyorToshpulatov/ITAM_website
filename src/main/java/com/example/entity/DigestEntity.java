package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "digest")
public class DigestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // ## nomi ##
    @Column(nullable = false)
    private  String nameUz;

    @Column(nullable = false)
    private  String nameRu;

    @Column(nullable = false)
    private  String nameEn;

    // ## category ##
    @Column(name = "category_id")
    private  Integer categoryId;
    @ManyToOne
    @JoinColumn(name = "category_id",insertable = false,updatable = false)
    private  CategoryDigestEntity category;

    // ## attach
    @Column(name = "attach_id")
    private String attachId;
    @OneToOne
    @JoinColumn(name = "attach_id",insertable = false,updatable = false)
    private AttachEntity attach;

    // date
    @Column
    private LocalDate createdDate=LocalDate.now();

    @Column
    private Boolean visible=true;
}
