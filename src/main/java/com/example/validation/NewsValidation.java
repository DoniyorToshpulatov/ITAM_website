package com.example.validation;

import com.example.dto.NewsDTO;
import com.example.exp.NewsFieldException;

public class NewsValidation {
    public static void isValid(NewsDTO dto){
        if(dto.getNameEn()==null||dto.getNameEn().trim().length()<6){
            throw new NewsFieldException("NameEn is invalid!!");
        }
        if(dto.getNameRu()==null||dto.getNameRu().trim().length()<6){
            throw new NewsFieldException("NameRu is invalid!!");
        }
        if(dto.getNameUz()==null||dto.getNameUz().trim().length()<6){
            throw new NewsFieldException("NameUz is invalid!!");
        }
        if(dto.getLongEn()==null||dto.getLongEn().trim().length()<6){
            throw new NewsFieldException("LongEn is invalid!!");
        }
        if(dto.getLongRu()==null||dto.getLongRu().trim().length()<6){
            throw new NewsFieldException("LongRu is invalid!!");
        }
        if(dto.getLongUz()==null||dto.getLongUz().trim().length()<6){
            throw new NewsFieldException("LongUz is invalid!!");
        }
        if(dto.getAttachId()==null||dto.getAttachId().length()<8){
            throw new NewsFieldException("Image id exception!!");
        }
        if(dto.getCreatedDate()==null){
            throw new NewsFieldException("Created date not invalid!!");
        }
    }
}
