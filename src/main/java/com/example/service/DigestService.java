package com.example.service;

import com.example.dto.DigestDTO;
import com.example.entity.AttachEntity;
import com.example.entity.CategoryDigestEntity;
import com.example.entity.DigestEntity;
import com.example.enums.Lang;
import com.example.exp.ItemNotFoundException;
import com.example.repository.AttachRepository;
import com.example.repository.CategoryDigestRepository;
import com.example.repository.DigestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class DigestService {
    @Autowired
    private DigestRepository digestRepository;

    @Autowired
    private CategoryDigestRepository categoryDigestRepository;

    @Autowired
    private AttachRepository attachRepository;

    public void create(DigestDTO dto) {
        DigestEntity entity = toEntity(dto);
        digestRepository.save(entity);
    }


    public void update(DigestDTO dto, Integer id) {
        Optional<DigestEntity> optional = digestRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Digest nto found!!!");
        }
        DigestEntity entity=new DigestEntity();
        if(dto.getNameUz()!=null){
            entity.setNameUz(dto.getNameUz());
        }else{
            entity.setNameUz(optional.get().getNameUz());
        }
        if(dto.getCategoryId()!=null){
            entity.setCategoryId(dto.getCategoryId());
        }else{
            entity.setCategoryId(optional.get().getCategoryId());
        }
        if(dto.getNameRu()!=null){
            entity.setNameRu(dto.getNameRu());
        }else{
            entity.setNameRu(optional.get().getNameRu());
        }
        if(dto.getNameEn()!=null){
            entity.setNameEn(dto.getNameEn());
        }else{
            entity.setNameEn(optional.get().getNameEn());
        }
        entity.setId(id);
        entity.setCreatedDate(optional.get().getCreatedDate());
        entity.setAttachId(optional.get().getAttachId());
        digestRepository.save(entity);
    }

    public void updateAttach(String attachId,Integer id){
        Optional<DigestEntity> optional = digestRepository.findById(id);
        if(optional.isEmpty()){
            throw new ItemNotFoundException("Update digest file !!!");
        }
        digestRepository.update(attachId,id);
    }
    public void delete(Integer id){
        Optional<DigestEntity> optional = digestRepository.findById(id);
        if(optional.isEmpty()){
            throw new ItemNotFoundException("Update digest file !!!");
        }
        digestRepository.delete(id);
    }
    public void noDelete(Integer id){
        Optional<DigestEntity> optional = digestRepository.findById(id);
        if(optional.isEmpty()){
            throw new ItemNotFoundException("Update digest file !!!");
        }
        digestRepository.noDelete(id);
    }

    public List<DigestDTO> more(Integer id, Lang lang){
        List<DigestEntity> byCategoryId = digestRepository.findByCategoryId(id);
        List<DigestDTO> dtoList=new LinkedList<>();
        for(int i=0;i<byCategoryId.size();i++){
            DigestEntity entity = byCategoryId.get(i);
            switch (lang){
                case uz -> {
                    DigestDTO dto=new DigestDTO();
                    if(entity.getVisible()){
                        dto.setNameUz(entity.getNameUz());
                        dto.setAttachId(entity.getAttachId());
                        dtoList.add(dto);
                    }
                }
                case ru -> {
                    DigestDTO dto=new DigestDTO();
                    if(entity.getVisible()){
                        dto.setNameRu(entity.getNameRu());
                        dto.setAttachId(entity.getAttachId());
                        dtoList.add(dto);
                    }
                }
                case en -> {
                    DigestDTO dto=new DigestDTO();
                    if(entity.getVisible()){
                        dto.setNameEn(entity.getNameEn());
                        dto.setAttachId(entity.getAttachId());
                        dtoList.add(dto);
                    }
                }
            }
        }
        return dtoList;
    }
    public DigestEntity toEntity(DigestDTO dto) {
        DigestEntity entity = new DigestEntity();
        entity.setNameUz(dto.getNameUz());
        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        Optional<CategoryDigestEntity> byId = categoryDigestRepository.findById(dto.getCategoryId());
        if (byId.isEmpty()) {
            throw new ItemNotFoundException("Category is not found!!!");
        }
        Optional<AttachEntity> optional = attachRepository.findById(dto.getAttachId());
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Attach is not found!!!");
        }
        entity.setCategoryId(dto.getCategoryId());
        entity.setAttachId(dto.getAttachId());
        entity.setCreatedDate(LocalDate.now());
        return entity;
    }
}
