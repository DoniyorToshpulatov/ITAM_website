package com.example.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "result")
public class ResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "attach_id")
    private  String attachId;
    @OneToOne
    @JoinColumn(name = "attach_id",insertable = false,updatable = false)
    private AttachEntity attach;

    @Column
    private  String nameUz;
    @Column
    private  String nameRu;
    @Column
    private  String nameEn;

    @Column
    private  String titleUz;
    @Column
    private String titleRu;
    @Column
    private String titleEn;

    @Column
    private  Boolean visible=true;

    @Column
    private String descriptionUz;
    @Column
    private String descriptionRu;
    @Column
    private String descriptionEn;

    @Column
    private LocalDate  createdDate;
}
