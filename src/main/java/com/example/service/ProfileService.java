package com.example.service;

import com.example.dto.ProfileDTO;
import com.example.entity.AttachEntity;
import com.example.entity.ProfileEntity;
import com.example.enums.Lang;
import com.example.exp.AttachException;
import com.example.exp.ItemNotFoundException;
import com.example.repository.AttachRepository;
import com.example.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

   @Autowired
    private AttachRepository attachRepository;
    public void update(ProfileDTO dto){
        Optional<ProfileEntity> profile=profileRepository.findById(dto.getId());
        if(profile.isEmpty()){
            throw new  ItemNotFoundException("Item not found profile!!");
        }

        ProfileEntity entity=new ProfileEntity();
        entity.setId(dto.getId());
        if(dto.getEmail()!=null&&dto.getEmail().length()>8){
          entity.setEmail(dto.getEmail());
        }else {
            entity.setEmail(profile.get().getEmail());
        }
        if(dto.getPassword()!=null&&dto.getPassword().length()>=4){
            entity.setPassword(dto.getPassword());
        }else{
            entity.setPassword(profile.get().getPassword());
        }
        if(dto.getImageId()!=null){
            entity.setAttachId(dto.getImageId());
        }else {
            entity.setAttachId(profile.get().getAttachId());
        }
        if(dto.getNameEn()!=null&&dto.getNameEn().length()>3){
            entity.setNameEn(dto.getNameEn());
        }else {
            entity.setNameEn(profile.get().getNameEn());
        }
        if(dto.getNameRu()!=null&&dto.getNameRu().length()>3){
            entity.setNameRu(dto.getNameRu());
        }else {
            entity.setNameRu(profile.get().getNameRu());
        }
        if(dto.getNameUz()!=null&&dto.getNameUz().length()>3){
            entity.setNameUz(dto.getNameUz());
        }else {
            entity.setNameUz(profile.get().getNameUz());
        }
        if(dto.getSurnameUz()!=null&&dto.getSurnameUz().length()>3){
            entity.setSurnameUz(dto.getSurnameUz());
        }else {
            entity.setSurnameUz(profile.get().getSurnameUz());
        }
        if(dto.getSurnameEn()!=null&&dto.getSurnameEn().length()>3){
            entity.setSurnameEn(dto.getSurnameEn());
        }else {
            entity.setSurnameEn(profile.get().getSurnameEn());
        }
        if(dto.getSurnameRu()!=null&&dto.getSurnameRu().length()>3){
            entity.setSurnameRu(dto.getSurnameRu());
        }else {
            entity.setSurnameRu(profile.get().getSurnameRu());
        }
        if(dto.getJobEn()!=null){
            entity.setJobEn(dto.getJobEn());
        }else {
            entity.setJobEn(profile.get().getJobEn());
        }
        if(dto.getJobRu()!=null){
            entity.setJobRu(dto.getJobRu());
        }else {
            entity.setJobRu(profile.get().getJobRu());
        }
        if(dto.getJobUz()!=null){
            entity.setJobUz(dto.getJobUz());
        }else {
            entity.setJobUz(profile.get().getJobUz());
        }
        entity.setRole(profile.get().getRole());
        entity.setStatus(profile.get().getStatus());
        profileRepository.save(entity);
    }
    public void updateAttachId(String attachId,Integer id){
        Optional<ProfileEntity> optional=profileRepository.findById(id);
        if(optional.isEmpty()){
            throw new ItemNotFoundException("Profile Item not find!!!");
        }
        Optional<AttachEntity> attach=attachRepository.findById(attachId);
        if(attach.isEmpty()){
            throw new AttachException("Attach not found!!!");
        }
        profileRepository.update(attachId,id);

    }

    public List<ProfileDTO> byDeportment(Lang lang ,Integer deportmentId){
        List<ProfileEntity> byDeportment = profileRepository.getByDeportment(deportmentId);
        List<ProfileDTO> dtoList=new LinkedList<>();
       switch (lang){
           case uz -> {
               for(int i=0;i<byDeportment.size();i++){
                   ProfileEntity entity = byDeportment.get(i);
                   ProfileDTO dto=new ProfileDTO();
                   dto.setId(entity.getId());
                   dto.setImageId(entity.getAttachId());
                   dto.setJobUz(entity.getJobUz());
                   dto.setNameUz(entity.getNameUz());
                   dto.setSurnameUz(entity.getSurnameUz());
                   dto.setWorkRole(entity.getWorkRole().toString());
                   dtoList.add(dto);
               }
           }
           case ru -> {
               for(int i=0;i<byDeportment.size();i++){
                   ProfileEntity entity = byDeportment.get(i);
                   ProfileDTO dto=new ProfileDTO();
                   dto.setId(entity.getId());
                   dto.setImageId(entity.getAttachId());
                   dto.setJobRu(entity.getJobRu());
                   dto.setNameRu(entity.getNameRu());
                   dto.setSurnameRu(entity.getSurnameRu());
                   dto.setWorkRole(entity.getWorkRole().toString());
                   dtoList.add(dto);
               }
           }
           case en -> {
               for(int i=0;i<byDeportment.size();i++){
                   ProfileEntity entity = byDeportment.get(i);
                   ProfileDTO dto=new ProfileDTO();
                   dto.setId(entity.getId());
                   dto.setImageId(entity.getAttachId());
                   dto.setJobEn(entity.getJobEn());
                   dto.setNameEn(entity.getNameEn());
                   dto.setSurnameEn(entity.getSurnameEn());
                   dto.setWorkRole(entity.getWorkRole().toString());
                   dtoList.add(dto);
               }
           }
       }
       return  dtoList;
    }

    public ProfileDTO byId(Integer id,Lang lang){
        Optional<ProfileEntity> optional = profileRepository.findById(id);
        if(optional.isEmpty()){
            throw  new ItemNotFoundException("Profile is not found!!");
        }
        ProfileDTO dto=new ProfileDTO();
        switch (lang){
            case uz -> {
                ProfileEntity entity = optional.get();
                dto.setId(id);
                dto.setNameUz(entity.getNameUz());
                dto.setVisible(entity.getVisible());
                dto.setImageId(entity.getAttachId());
                dto.setSurnameUz(entity.getSurnameUz());
                dto.setWorkRole(entity.getWorkRole().toString());
                dto.setSurnameUz(entity.getSurnameUz());
                dto.setDeportmentId(entity.getDeportmentId());
                dto.setJobUz(entity.getJobUz());
                dto.setRole(entity.getRole().toString());
            }
            case ru -> {
                ProfileEntity entity = optional.get();
                dto.setId(id);
                dto.setNameRu(entity.getNameRu());
                dto.setVisible(entity.getVisible());
                dto.setImageId(entity.getAttachId());
                dto.setSurnameRu(entity.getSurnameRu());
                dto.setWorkRole(entity.getWorkRole().toString());
                dto.setSurnameRu(entity.getSurnameRu());
                dto.setDeportmentId(entity.getDeportmentId());
                dto.setJobRu(entity.getJobRu());
                dto.setRole(entity.getRole().toString());
            }
            case en -> {
                ProfileEntity entity = optional.get();
                dto.setId(id);
                dto.setNameEn(entity.getNameEn());
                dto.setVisible(entity.getVisible());
                dto.setImageId(entity.getAttachId());
                dto.setSurnameEn(entity.getSurnameEn());
                dto.setWorkRole(entity.getWorkRole().toString());
                dto.setSurnameEn(entity.getSurnameEn());
                dto.setDeportmentId(entity.getDeportmentId());
                dto.setJobEn(entity.getJobEn());
                dto.setRole(entity.getRole().toString());
            }
        }
        return dto;
    }
}
