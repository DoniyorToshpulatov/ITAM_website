package com.example.service;

import com.example.dto.PartnerDTO;
import com.example.entity.PartnerEntity;
import com.example.enums.Lang;
import com.example.exp.ItemNotFoundException;
import com.example.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.Inet4Address;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class PartnerService {
    @Autowired
    private PartnerRepository partnerRepository;

    public  void create(PartnerDTO dto){
        PartnerEntity entity = toEntity(dto);
        partnerRepository.save(entity);
    }

    public void  update(PartnerDTO dto, Integer id){
        Optional<PartnerEntity> optional = partnerRepository.findById(id);
        if(optional.isEmpty()){
            throw new ItemNotFoundException("Is not found partner!!");
        }
        PartnerEntity entity=new PartnerEntity();
        if(optional.get().getVisible()){

            if(dto.getLink()!=null){
                entity.setLink(dto.getLink());
            }else{
                entity.setLink(optional.get().getLink());
            }

            if(dto.getNameEn()!=null){
                entity.setNameEn(dto.getNameEn());
            }else {
                entity.setNameEn(optional.get().getNameEn());
            }

            if(dto.getNameRu()!=null){
                entity.setNameRu(dto.getNameRu());
            }else {
                entity.setNameRu(optional.get().getNameRu());
            }

            if(dto.getNameUz()!=null){
                entity.setNameRu(dto.getNameUz());
            }else {
                entity.setNameRu(optional.get().getNameUz());
            }

            entity.setId(optional.get().getId());
            entity.setVisible(optional.get().getVisible());
            entity.setCreatedDate(optional.get().getCreatedDate());
            entity.setAttachId(optional.get().getAttachId());
            partnerRepository.save(entity);
        }
    }

    public void  updateAttachId(String attachId,Integer id){
        Optional<PartnerEntity> optional = partnerRepository.findById(id);
        if(optional.isEmpty()){
            throw new ItemNotFoundException("Is not found partner!!");
        }
        partnerRepository.update(attachId,id);
    }

    public void  delete(Integer id){
        Optional<PartnerEntity> optional = partnerRepository.findById(id);
        if(optional.isEmpty()){
            throw new ItemNotFoundException("Is not found partner!!");
        }
        partnerRepository.delete(id);
    }

    public void  noDelete(Integer id){
        Optional<PartnerEntity> optional = partnerRepository.findById(id);
        if(optional.isEmpty()){
            throw new ItemNotFoundException("Is not found partner!!");
        }
        partnerRepository.noDelete(id);
    }

    public List<PartnerDTO> more(Lang lang){
        List<PartnerEntity> all = partnerRepository.findAll();
        List<PartnerDTO> dtoList=new LinkedList<>();
        for(int i=0;i<all.size();i++){
            PartnerEntity entity = all.get(i);
            PartnerDTO dto=new PartnerDTO();
            switch (lang){
                case ru -> {
                    dto.setLink(entity.getLink());
                    dto.setAttachId(entity.getAttachId());
                    dto.setNameRu(entity.getNameRu());
                    dtoList.add(dto);
                } case en -> {
                    dto.setLink(entity.getLink());
                    dto.setAttachId(entity.getAttachId());
                    dto.setNameEn(entity.getNameEn());
                    dtoList.add(dto);
                }
                case uz -> {
                    dto.setLink(entity.getLink());
                    dto.setAttachId(entity.getAttachId());
                    dto.setNameUz(entity.getNameUz());
                    dtoList.add(dto);
                }

            }
        }
        return dtoList;
    }

    public  PartnerDTO byId(Integer id,Lang lang){
        Optional<PartnerEntity> optional = partnerRepository.findById(id);
        if(optional.isEmpty()){
            throw new ItemNotFoundException("Is not found partner!!");
        }
        PartnerEntity entity = optional.get();
        PartnerDTO dto=new PartnerDTO();
        switch (lang){
            case uz -> {
                dto.setNameUz(entity.getNameUz());
                dto.setAttachId(entity.getAttachId());
                dto.setLink(entity.getLink());
            }
            case ru -> {
                dto.setNameRu(entity.getNameRu());
                dto.setAttachId(entity.getAttachId());
                dto.setLink(entity.getLink());
            }
            case en -> {
                dto.setNameEn(entity.getNameEn());
                dto.setAttachId(entity.getAttachId());
                dto.setLink(entity.getLink());
            }
        }
        return dto;
    }
    public PartnerEntity toEntity(PartnerDTO dto){
        PartnerEntity entity=new PartnerEntity();
        entity.setLink(dto.getLink());
        entity.setAttachId(dto.getAttachId());
        entity.setVisible(true);
        entity.setCreatedDate(LocalDate.now());
        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        entity.setNameUz(dto.getNameUz());
        return entity;
    }

}
