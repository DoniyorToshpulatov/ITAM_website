package com.example.validation;

import com.example.dto.RegistrationDTO;
import com.example.exp.AppBadRequestException;

public class RegistrationValidation {
    public static void isValid (RegistrationDTO dto) {
        if (dto.getNameUz() == null || dto.getNameUz().trim().isEmpty() || dto.getNameUz().trim().length() < 3) {
            throw new AppBadRequestException("Name Not Valid");
        }
        if (dto.getNameRu() == null || dto.getNameRu().trim().isEmpty() || dto.getNameRu().trim().length() < 3) {
            throw new AppBadRequestException("Name Not Valid");
        }
        if (dto.getNameEn() == null || dto.getNameEn().trim().isEmpty() || dto.getNameEn().trim().length() < 3) {
            throw new AppBadRequestException("Name Not Valid");
        }
        if (dto.getSurnameUz() == null || dto.getSurnameUz().trim().isEmpty() || dto.getSurnameUz().trim().length() < 3) {
            throw new AppBadRequestException("Surname Not Valid");
        }
        if (dto.getEmail() == null || dto.getEmail().trim().isEmpty() || dto.getEmail().trim().length() < 3) {
            throw new AppBadRequestException("Email Not Valid");
        }
        if (dto.getPassword() == null || dto.getPassword().trim().isEmpty() || dto.getPassword().trim().length() < 8) {
            throw new AppBadRequestException("Password Not Valid");
        }
    }
}
