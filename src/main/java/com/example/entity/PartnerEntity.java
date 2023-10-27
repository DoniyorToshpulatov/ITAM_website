package com.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "partner")
public class PartnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column
    private  String link;

    @Column
    private  String nameUz;

    @Column
    private  String nameRu;

    @Column
    private  String nameEn;

    //date
    @Column
    private LocalDate createdDate;

    @Column
    private Boolean visible=true;

    // ###logatip###
    @Column(name = "attach_id")
    private String attachId;
    @OneToOne
    @JoinColumn(name = "attach_id",updatable = false,insertable = false)
    private AttachEntity attach;

}
