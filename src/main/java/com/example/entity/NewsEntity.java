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
@Table(name = "news")
public class NewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "attach_id")
    private String attachId;
    @OneToOne
    @JoinColumn(name = "attach_id",insertable = false,updatable = false)
    private AttachEntity attach;

    // ###nomi###
    @Column
    private String nameUz;

    @Column
    private String nameRu;

    @Column
    private String nameEn;

    // ###qisqachasi###
    @Column
    private String shortUz;

    @Column
    private String shortRu;

    @Column
    private String shortEn;

    // ###tuliq###
    @Column
    private String longUz;

    @Column
    private String longRu;

    @Column
    private String longEn;

    // date
    @Column
    private LocalDate createdDate;

    @Column
    private Boolean visible=true;


}
