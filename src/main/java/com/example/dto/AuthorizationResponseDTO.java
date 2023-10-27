package com.example.dto;

import com.example.enums.ProfileRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorizationResponseDTO {


    private String nameUz;
    private  String nameRu;
    private  String nameEn;

    private String surnameUz;
    private String surnameRu;
    private  String surnameEn;

    private String jobUz;
    private String jobRu;
    private String jobEn;

    private ProfileRole role;

    private String token;
}
