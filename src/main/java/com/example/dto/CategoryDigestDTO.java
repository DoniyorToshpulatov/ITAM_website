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
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDigestDTO {
    private  Integer id;
    //## nomi ##
    private  String nameUz;
    private  String nameRu;
    private String nameEn;

    // ### attach ##
    private String attachId;
    private AttachDTO attach;

    // ##kurinish##
    private Boolean visible;

    private LocalDateTime createdDate;
}
