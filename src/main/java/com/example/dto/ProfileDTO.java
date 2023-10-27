package com.example.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {
    private Integer id;
    private String nameUz;
    private  String nameRu;
    private  String nameEn;

    private String surnameUz;
    private String surnameRu;
    private String surnameEn;

    private  String jobUz;
    private  String jobRu;
    private  String jobEn;

    private  Boolean visible;

    private String email;
    private String password;
    private String role;
    private String workRole;
    private String imageId;
    private String jwt;
    private  Integer deportmentId;
}
