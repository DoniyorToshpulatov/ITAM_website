package com.example.service;

import com.example.dto.CategoryDigestDTO;
import com.example.entity.CategoryDigestEntity;
import com.example.enums.Lang;
import com.example.exp.ItemNotFoundException;
import com.example.repository.CategoryDigestRepository;
import jakarta.servlet.http.PushBuilder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryDigestService {
    @Autowired
    private CategoryDigestRepository categoryDigestRepository;

    public void create(CategoryDigestDTO dto){
        CategoryDigestEntity entity = toEntity(dto);
        categoryDigestRepository.save(entity);
    }

    public  void update(CategoryDigestDTO dto,Integer id){
        Optional<CategoryDigestEntity> optional = categoryDigestRepository.findById(id);
        if(optional.isEmpty()){
            throw  new ItemNotFoundException("Category is not found!!");
        }
        CategoryDigestEntity entity=new CategoryDigestEntity();
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

        if(dto.getNameUz()!=null){
            entity.setNameUz(dto.getNameUz());
        }else{
            entity.setNameUz(optional.get().getNameUz());
        }
        if(dto.getAttachId()!=null){
            entity.setAttachId(dto.getAttachId());
        }else{
            entity.setAttachId(optional.get().getAttachId());
        }

        entity.setId(id);
        entity.setVisible(optional.get().getVisible());
        entity.setCreatedDate(optional.get().getCreatedDate());
        categoryDigestRepository.save(entity);
    }

    public void updateAttach(String attachId,Integer id){
        Optional<CategoryDigestEntity> optional = categoryDigestRepository.findById(id);
        if(optional.isEmpty()){
            throw new ItemNotFoundException("Category is not found!!");
        }
        categoryDigestRepository.update(attachId,id);
    }

    public void  delete(Integer id){
        Optional<CategoryDigestEntity> optional = categoryDigestRepository.findById(id);
        if(optional.isEmpty()){
            throw new ItemNotFoundException("Category is not found!!");
        }
        categoryDigestRepository.delete(id);
    }

    public void  noDelete(Integer id){
        Optional<CategoryDigestEntity> optional = categoryDigestRepository.findById(id);
        if(optional.isEmpty()){
            throw new ItemNotFoundException("Category is not found!!");
        }
        categoryDigestRepository.noDelete(id);
    }

    public List<CategoryDigestDTO> more(Lang lang){
        List<CategoryDigestEntity> all = categoryDigestRepository.findAll();
        List<CategoryDigestDTO> dtoList=new LinkedList<>();
        for(int i=0;i<all.size();i++){
            CategoryDigestEntity entity = all.get(i);
            switch (lang){
                case uz -> {
                    CategoryDigestDTO dto=new CategoryDigestDTO();
                    if(entity.getVisible()) {
                        dto.setNameUz(entity.getNameUz());
                        dto.setAttachId(entity.getAttachId());
                        dtoList.add(dto);
                    }
                }
                case ru -> {
                    CategoryDigestDTO dto=new CategoryDigestDTO();
                    if(entity.getVisible()) {
                        dto.setNameRu(entity.getNameRu());
                        dto.setAttachId(entity.getAttachId());
                        dtoList.add(dto);
                    }
                }
                case en -> {
                    CategoryDigestDTO dto=new CategoryDigestDTO();
                    if(entity.getVisible()) {
                        dto.setNameEn(entity.getNameEn());
                        dto.setAttachId(entity.getAttachId());
                        dtoList.add(dto);
                    }
                }
                default -> System.out.println("bunaqasi yuku!!");
            }
        }
        return dtoList;
    }
    public CategoryDigestEntity toEntity(CategoryDigestDTO dto){
        CategoryDigestEntity entity=new CategoryDigestEntity();
        entity.setVisible(true);
        entity.setAttachId(dto.getAttachId());
        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        entity.setNameUz(dto.getNameUz());
        entity.setCreatedDate(LocalDate.now());
        return  entity;
    }
}
