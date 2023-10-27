package com.example.service;

import com.example.dto.AuthorizationDTO;
import com.example.dto.AuthorizationResponseDTO;
import com.example.dto.JwtEmailChangeDTO;
import com.example.dto.RegistrationDTO;
import com.example.entity.ProfileEntity;
import com.example.enums.Lang;
import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import com.example.enums.ProfileWorkRole;
import com.example.exp.AppBadRequestException;
import com.example.exp.EmailAlreadyExistsException;
import com.example.exp.ProfileException;
import com.example.repository.AttachRepository;
import com.example.repository.ProfileRepository;

import com.example.util.JwtTokenUtil;
import com.example.util.MD5util;
import com.example.validation.RegistrationValidation;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.util.MD5util.encode;

@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ResourceBundleService resourceBundleService;
    @Autowired
    private AttachRepository attachRepository;

    public void registration(RegistrationDTO dto) {
        RegistrationValidation.isValid(dto);
            ProfileEntity entity = new ProfileEntity();
            //name
            entity.setNameUz(dto.getNameUz());
            entity.setNameRu(dto.getNameRu());
            entity.setNameEn(dto.getNameEn());
            // surname
            entity.setSurnameUz(dto.getSurnameUz());
            entity.setSurnameRu(dto.getSurnameRu());
            entity.setSurnameEn(dto.getSurnameEn());

            entity.setVisible(true);

            entity.setJobUz(dto.getJobUz());
            entity.setJobEn(dto.getJobEn());
            entity.setJobRu(dto.getJobRu());

            entity.setEmail(dto.getEmail() );

            String pswd = encode(dto.getPassword());
            entity.setPassword(pswd);
            entity.setAttachId(dto.getImageId());
            if(dto.getRole().trim().toLowerCase().equals("user".toLowerCase())){
                entity.setRole(ProfileRole.ROLE_USER);
            } else if (dto.getRole().trim().toLowerCase().equals("moderator".toLowerCase())){
            entity.setRole(ProfileRole.ROLE_MODERATOR);
            } else if (dto.getRole().trim().toLowerCase().equals("admin".toLowerCase())) {
                entity.setRole(ProfileRole.ROLE_ADMIN);
            }
            entity.setStatus(ProfileStatus.ACTIVE);
            if(dto.getWorkRole().trim().toLowerCase().equals("employee".toLowerCase())){
                entity.setWorkRole(ProfileWorkRole.EMPLOYEE);
            }else if(dto.getWorkRole().trim().toLowerCase().equals("zam".toLowerCase())){
                entity.setWorkRole(ProfileWorkRole.ZAM);
            } else if (dto.getWorkRole().trim().toLowerCase().equals("director".toLowerCase())) {
                entity.setWorkRole(ProfileWorkRole.DIRECTOR);
            }

            entity.setAttachId(dto.getImageId());
            entity.setDeportmentId(dto.getDeportmentId());
            profileRepository.save(entity);

    }


    public AuthorizationResponseDTO authorization(AuthorizationDTO dto, Lang lang) {
        Optional<ProfileEntity> byEntity = profileRepository.findByEmailAndPassword(dto.getEmail(), MD5util.encode(dto.getPassword()));
        if(byEntity.isEmpty())
            throw  new ProfileException( resourceBundleService.getMessage( "item.not.found",lang.name()));

        if(byEntity.get().getStatus().equals(ProfileStatus.BLOCK))
            throw new ProfileException(resourceBundleService.getMessage( "profile.block",lang.name()));



        ProfileEntity profile = byEntity.get();

        AuthorizationResponseDTO respose = new AuthorizationResponseDTO();
        respose.setNameUz(profile.getNameUz());
        respose.setNameRu(profile.getNameRu());
        respose.setNameEn(profile.getNameEn());

        respose.setSurnameUz(profile.getSurnameUz());
        respose.setSurnameRu(profile.getSurnameRu());
        respose.setSurnameEn(profile.getSurnameEn());
        respose.setRole(profile.getRole());
        respose.setToken(JwtTokenUtil.encode(profile.getEmail(), profile.getRole()));

        return respose;




    }
    private void checkParameters(RegistrationDTO dto) {
        if (dto.getNameUz() == null || dto.getNameUz().isBlank() || dto.getNameUz().trim().length() <= 3) {
            throw new AppBadRequestException("Ism xato kiritilgan");
        }
        if (dto.getNameEn() == null || dto.getNameEn().isBlank() || dto.getNameEn().trim().length() <= 3) {
            throw new AppBadRequestException("Name is wrong!!");
        }
        if (dto.getNameRu() == null || dto.getNameRu().isBlank() || dto.getNameRu().trim().length() <= 3) {
            throw new AppBadRequestException("Имя введено неправильно");
        }
        if (dto.getSurnameUz() == null || dto.getSurnameUz().isBlank() || dto.getSurnameUz().trim().length() <= 3) {
            throw new AppBadRequestException("Familiya noto'g'ri!!");
        }
        if (dto.getSurnameRu() == null || dto.getSurnameRu().isBlank() || dto.getSurnameRu().trim().length() <= 3) {
            throw new AppBadRequestException("Неверная фамилия!!");
        }
        if (dto.getSurnameEn() == null || dto.getSurnameEn().isBlank() || dto.getSurnameEn().trim().length() <= 3) {
            throw new AppBadRequestException("Surname is wrong");
        }
        if (dto.getPassword() == null || dto.getPassword().isBlank() || dto.getPassword().trim().length() <= 3) {
            throw new AppBadRequestException("Password is wrong");
        }
        if (dto.getEmail() == null || dto.getEmail().isBlank() || dto.getEmail().trim().length() <= 3) {
            throw new AppBadRequestException("Phone is wrong");
        }
    }

    public String verification(String token) {
        Integer id;
        try {
            id = JwtTokenUtil.decodeMailGetUserId(token);
        } catch (JwtException e) {
            return "Verification failed";
        }

        Optional<ProfileEntity> byId = profileRepository.findById(id);
        ProfileEntity entity = byId.get();
      /*  if (!exists.getStatus().equals(ProfileStatus.NOT_ACTIVE)) {
            return "Verification failed";
        }

      */
        entity.setStatus(ProfileStatus.ACTIVE);
        profileRepository.save(entity);

        return "Verification success";
    }

    public String verificationEmailChange(String token) {


        JwtEmailChangeDTO jwtEmailChangeDTO;
        try {
            jwtEmailChangeDTO  = JwtTokenUtil.decodeMailGetUserIdAndEmailAddress(token);
        } catch (JwtException e) {
            return "Verification failed";
        }

        Optional<ProfileEntity> byId = profileRepository.findById(jwtEmailChangeDTO.getId());
        ProfileEntity entity = byId.get();
      /*  if (!exists.getStatus().equals(ProfileStatus.NOT_ACTIVE)) {
            return "Verification failed";
        }

      */
        entity.setEmail(jwtEmailChangeDTO.getEmail());
        profileRepository.save(entity);

        return "change success";
    }
    }

