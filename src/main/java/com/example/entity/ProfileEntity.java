package com.example.entity;

import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import com.example.enums.ProfileWorkRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "profile")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column
    private String nameUz; // ism

    @Column
    private String nameRu;// ism

    @Column
    private String nameEn;// ism

    @Column
    private String surnameUz;// familya

    @Column
    private String surnameRu;// familya

    @Column
    private String surnameEn;// familya

    @Column
    private String email;

    @Column
    private String password;


    @Enumerated(EnumType.STRING)
    @Column
    private ProfileRole role;

    @Column
    private String jobUz;

    @Column
    private String jobRu;

    @Column
    private String jobEn;

    @Column(name = "deportment_id")
    private Integer deportmentId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deportment_id",insertable = false,updatable = false)
    private DeportmentEntity deportment;

    @Enumerated(EnumType.STRING)
    @Column
    private  ProfileStatus status;

    @Column(name = "attach_id")
    private  String attachId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attach_id",insertable = false,updatable = false)
    private  AttachEntity attach;

    @Column
    private Boolean visible=true;


    @Enumerated(EnumType.STRING)
    @Column
    private ProfileWorkRole workRole;

}
