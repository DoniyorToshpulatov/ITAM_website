package com.example.dto;


import com.example.entity.AttachEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultDTO {
    private Integer id;

    private  String attachId;
    private AttachEntity attach;

    private  String nameUz;
    private  String nameRu;
    private  String nameEn;


    private  String titleUz;
    private String titleRu;
    private String titleEn;


    private  Boolean visible;

    private String descriptionUz;
    private String descriptionRu;
    private String descriptionEn;

    private String createdDate;
}
