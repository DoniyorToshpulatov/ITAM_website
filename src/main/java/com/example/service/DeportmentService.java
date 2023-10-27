package com.example.service;

import com.example.dto.DeportmentDTO;
import com.example.entity.DeportmentEntity;
import com.example.enums.Lang;
import com.example.exp.ItemNotFoundException;
import com.example.repository.DeportmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class DeportmentService {
    @Autowired
    private DeportmentRepository deportmentRepository;

    public void create(DeportmentDTO dto) {
        DeportmentEntity entity = toEntity(dto);
        deportmentRepository.save(entity);
    }

    public void update(DeportmentDTO dto, Integer id) {
        Optional<DeportmentEntity> optional = deportmentRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Deportment is not found!!");
        }
        DeportmentEntity entity = new DeportmentEntity();

        if (dto.getNameEn() != null) {
            entity.setNameEn(dto.getNameEn());
        } else {
            entity.setNameEn(optional.get().getNameEn());
        }

        if (dto.getNameRu() != null) {
            entity.setNameRu(dto.getNameRu());
        } else {
            entity.setNameRu(optional.get().getNameRu());
        }

        if (dto.getNameUz() != null) {
            entity.setNameUz(dto.getNameUz());
        } else {
            entity.setNameUz(optional.get().getNameUz());
        }
        if (dto.getPurposeEn() != null) {
            entity.setPurposeEn(optional.get().getPurposeEn());
        } else {
            entity.setPurposeEn(optional.get().getPurposeEn());
        }
        if (dto.getPurposeRu() != null) {
            entity.setPurposeRu(optional.get().getPurposeRu());
        } else {
            entity.setPurposeRu(optional.get().getPurposeRu());
        }
        if (dto.getPurposeUz() != null) {
            entity.setPurposeUz(optional.get().getPurposeUz());
        } else {
            entity.setPurposeUz(optional.get().getPurposeUz());
        }
        if (dto.getStatuteEn() != null) {
            entity.setStatuteEn(dto.getStatuteEn());
        } else {
            entity.setStatuteEn(optional.get().getStatuteEn());
        }
        if (dto.getStatuteRu() != null) {
            entity.setStatuteRu(dto.getStatuteRu());
        } else {
            entity.setStatuteRu(optional.get().getStatuteRu());
        }
        if (dto.getStatuteUz() != null) {
            entity.setStatuteUz(dto.getStatuteUz());
        } else {
            entity.setStatuteUz(optional.get().getStatuteUz());
        }

        if (dto.getProfileId() != null) {
            entity.setProfileId(dto.getProfileId());
        } else {
            entity.setProfileId(optional.get().getProfileId());
        }
        if (dto.getTaskEn() != null) {
            entity.setTaskEn(dto.getTaskEn());
        } else {
            entity.setTaskEn(optional.get().getTaskEn());
        }
        if (dto.getTaskRu() != null) {
            entity.setTaskRu(dto.getTaskRu());
        } else {
            entity.setTaskRu(optional.get().getTaskRu());
        }
        if (dto.getTaskUz() != null) {
            entity.setTaskUz(dto.getTaskUz());
        } else {
            entity.setTaskUz(optional.get().getTaskUz());
        }
        if (dto.getResultEn() != null) {
            entity.setResultEn(dto.getResultEn());
        } else {
            entity.setResultEn(optional.get().getResultEn());
        }

        if (dto.getResultUz() != null) {
            entity.setResultUz(dto.getResultUz());
        } else {
            entity.setResultUz(optional.get().getResultUz());
        }

        if (dto.getResultRu() != null) {
            entity.setResultRu(dto.getResultRu());
        } else {
            entity.setResultRu(optional.get().getResultRu());
        }
        entity.setId(id);
        entity.setVisible(true);
        entity.setAttachId(optional.get().getAttachId());
        deportmentRepository.save(entity);
    }

    public void updateAttach(Integer id, String attachId) {
        Optional<DeportmentEntity> optional = deportmentRepository.findById(id);
        if (optional.isEmpty()) {
        throw new ItemNotFoundException("Deportment is not found!!");
        }
        deportmentRepository.update(attachId,id);
    }

    public  void  delete(Integer id){
        Optional<DeportmentEntity> optional = deportmentRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Deportment is not found!!");
        }
        deportmentRepository.delete(id);
    }
    public  void  noDelete(Integer id){
        Optional<DeportmentEntity> optional = deportmentRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Deportment is not found!!");
        }
        deportmentRepository.noDelete(id);
    }

    public List<DeportmentDTO> more(Lang lang){
        List<DeportmentEntity> entityList=deportmentRepository.findAll();
        List<DeportmentDTO> dtoList=new LinkedList<>();
        for(int i=0;i<entityList.size();i++){
            DeportmentEntity entity = entityList.get(i);
            switch (lang){
                case en -> {
                    DeportmentDTO dto = toDtoEn(entity);
                    if(dto!=null){
                        dtoList.add(dto);
                    }
                }
                case ru -> {
                    DeportmentDTO dto = toDtoRu(entity);
                    if(dto!=null){
                        dtoList.add(dto);
                    }
                }
                case uz -> {
                    DeportmentDTO dto = toDtoUz(entity);
                    if(dto!=null){
                        dtoList.add(dto);
                    }
                }
                default -> {
                    System.out.println("Bunaqasi yuq!!");
                }
            }
        }
        return dtoList;

    }

    public  DeportmentDTO toDtoUz(DeportmentEntity entity){
        if(entity.getVisible()) {
        DeportmentDTO dto=new DeportmentDTO();
        dto.setAttachId(entity.getAttachId());
        dto.setNameUz(entity.getNameUz());
        dto.setProfileId(entity.getProfileId());
        dto.setPurposeUz(entity.getPurposeUz());
        dto.setResultUz(entity.getResultUz());
        dto.setTaskUz(entity.getTaskUz());
        dto.setStatuteUz(entity.getStatuteUz());
        return dto;
        }else {
            return null;
        }
    }
    public  DeportmentDTO toDtoEn(DeportmentEntity entity){
        if(entity.getVisible()) {
            DeportmentDTO dto = new DeportmentDTO();
            dto.setAttachId(entity.getAttachId());
            dto.setNameEn(entity.getNameEn());
            dto.setProfileId(entity.getProfileId());
            dto.setPurposeEn(entity.getPurposeEn());
            dto.setResultEn(entity.getResultEn());
            dto.setTaskEn(entity.getTaskEn());
            dto.setStatuteEn(entity.getStatuteEn());
            return dto;
        }else {
            return null;
        }
    }
    public  DeportmentDTO toDtoRu(DeportmentEntity entity){
        if(entity.getVisible()) {
        DeportmentDTO dto=new DeportmentDTO();
        dto.setAttachId(entity.getAttachId());
        dto.setNameRu(entity.getNameRu());
        dto.setProfileId(entity.getProfileId());
        dto.setPurposeRu(entity.getPurposeRu());
        dto.setResultRu(entity.getPurposeRu());
        dto.setTaskRu(entity.getTaskRu());
        dto.setStatuteRu(entity.getStatuteRu());
        return  dto;
        }else {
            return null;
        }
    }
    public DeportmentEntity toEntity(DeportmentDTO dto) {
        DeportmentEntity entity = new DeportmentEntity();

        entity.setAttachId(dto.getAttachId());

        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        entity.setNameUz(dto.getNameUz());

        entity.setProfileId(dto.getProfileId());

        entity.setPurposeEn(dto.getPurposeEn());
        entity.setPurposeRu(dto.getPurposeRu());
        entity.setPurposeUz(dto.getPurposeUz());

        entity.setResultEn(dto.getResultEn());
        entity.setResultUz(dto.getResultUz());
        entity.setResultRu(dto.getPurposeRu());

        entity.setVisible(true);

        entity.setTaskUz(dto.getTaskUz());
        entity.setTaskRu(dto.getTaskRu());
        entity.setTaskEn(dto.getTaskEn());

        entity.setStatuteEn(dto.getStatuteEn());
        entity.setStatuteRu(dto.getStatuteRu());
        entity.setStatuteUz(dto.getStatuteUz());

        return entity;
    }
}
