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
public class PartnerDTO {
    private  Integer id;
    private  String link;

    // name
    private  String nameUz;
    private  String nameRu;
    private  String nameEn;

    // date
    private String createdDate;

    // ###logatip###
    private String attachId;
    private AttachDTO attach;
    private  Boolean visible;

}
