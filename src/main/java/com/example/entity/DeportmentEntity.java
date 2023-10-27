package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "deportment")
public class DeportmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nameUz;

    @Column
    private String nameRu;

    @Column
    private String nameEn;

    @Column
    private String taskUz;       //asosiy vazifalari
    @Column
    private String taskRu;       //asosiy vazifalari
    @Column
    private String taskEn;       //asosiy vazifalari

    @Column
    private String statuteUz;     // nizom

    @Column
    private String statuteRu;     // nizom

    @Column
    private String statuteEn;      // nizom


    @Column
    private String purposeUz;     // maqasadi

    @Column
    private String purposeRu;     // maqasadi

    @Column
    private String purposeEn;     // maqasadi


    @Column
    private  String resultUz;       //natijalari

    @Column
    private  String resultRu;       //natijalari

    @Column
    private  String resultEn;       //natijalari

    @Column(name = "profile_id")
    private  Integer profileId;
    @OneToOne
    @JoinColumn(name = "profile_id",insertable = false,updatable = false)
    private ProfileEntity boss;

    @Column(name = "attach_id")
    private  String attachId;
    @OneToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "attach_id",insertable = false,updatable = false)
    private AttachEntity attach;

    @Column
    private Boolean visible=true;

}
