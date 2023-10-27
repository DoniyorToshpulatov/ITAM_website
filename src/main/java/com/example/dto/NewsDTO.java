package com.example.dto;

import com.example.entity.AttachEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsDTO {

    private Integer id;
    private String attachId;
    private AttachDTO attach;

    // ###nomi###

    private String nameUz;
    private String nameRu;
    private String nameEn;

    // ###qisqachasi###
    private String shortUz;
    private String shortRu;
    private String shortEn;

    // ###tuliq###
    private String longUz;
    private String longRu;
    private String longEn;

    private  Boolean visible;
    // date
    private String createdDate;// 12-05-2023

}
