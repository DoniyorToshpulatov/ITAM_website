package com.example.dto;

import com.example.entity.AttachEntity;
import com.example.entity.ProfileEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeportmentDTO {

    private Integer id;
    private String nameUz;
    private String nameRu;
    private String nameEn;

    //asosiy vazifalari
    private String taskUz;
    private String taskRu;
    private String taskEn;

    // nizom
    private String statuteUz;
    private String statuteRu;
    private String statuteEn;

    // maqasadi
    private String purposeUz;
    private String purposeRu;
    private String purposeEn;


    //natijalari
    private  String resultUz;
    private  String resultRu;
    private  String resultEn;



    private  Integer profileId;
    private ProfileDTO boss;

    private Boolean visible;
    private  String attachId;
    private AttachEntity attach;

}
