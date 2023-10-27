package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Table(name = "category_digest")
public class CategoryDigestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    //## nomi ##
    @Column
    private  String nameUz;

    @Column
    private  String nameRu;

    @Column
    private String nameEn;

    // ### attach ##
    @Column(name = "attach_id")
    private String attachId;
    @OneToOne
    @JoinColumn(name = "attach_id",insertable = false,updatable = false)
    private AttachEntity attach;

    // ##kurinish##
    @Column
    private Boolean visible=true;

    // date
    @Column
    private LocalDate createdDate=LocalDate.now();

}
