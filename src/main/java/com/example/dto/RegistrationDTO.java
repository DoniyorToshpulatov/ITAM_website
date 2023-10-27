package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RegistrationDTO {
    private String nameUz;
    private String nameRu;
    private  String nameEn;

    private String surnameUz;
    private  String surnameRu;
    private String surnameEn;

    private  String jobUz;
    private String jobRu;
    private  String  jobEn;


    private  String imageId;
    private String email;
    private String password;
    private String role;
    private  String workRole;
    private  Integer deportmentId;
}
