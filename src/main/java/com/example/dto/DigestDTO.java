package com.example.dto;

import com.example.entity.AttachEntity;
import com.example.entity.CategoryDigestEntity;
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
public class DigestDTO {

    private Integer id;

    // ## nomi ##
    private  String nameUz;
    private  String nameRu;
    private  String nameEn;

    // ## category ##
    private  Integer categoryId;
    private CategoryDigestDTO category;

    // ## attach
    private String attachId;
    private AttachDTO attach;

    // date
    private LocalDateTime createdDate;
}
