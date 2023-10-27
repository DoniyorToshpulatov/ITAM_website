package com.example.service;

import com.example.dto.ResultDTO;
import com.example.entity.ResultEntity;
import com.example.enums.Lang;
import com.example.exp.ItemNotFoundException;
import com.example.repository.ResultRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ResultService {
    @Autowired
    private ResultRepository resultRepository;

    public void  create(ResultDTO dto){
        ResultEntity entity=new ResultEntity();
        entity.setAttachId(dto.getAttachId());

        entity.setCreatedDate(LocalDate.parse(dto.getCreatedDate()));

        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        entity.setNameUz(dto.getNameUz());

        entity.setTitleEn(dto.getTitleEn());
        entity.setTitleUz(dto.getTitleUz());
        entity.setTitleRu(dto.getTitleRu());

        entity.setVisible(true);

        entity.setDescriptionEn(dto.getDescriptionEn());
        entity.setDescriptionRu(dto.getDescriptionRu());
        entity.setDescriptionUz(dto.getDescriptionUz());

        resultRepository.save(entity);
    }

    public void update(ResultDTO dto, Integer id){
        Optional<ResultEntity> optional = resultRepository.findById(id);
        if(optional.isEmpty()){
            throw new ItemNotFoundException("Result is not found!!");
        }
        ResultEntity entity=new ResultEntity();
        if(optional.get().getVisible()){

            if(dto.getNameUz()!=null){
                entity.setNameUz(dto.getNameUz());
            }else{
                entity.setNameUz(optional.get().getNameUz());
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
            // title
            if(dto.getTitleEn()!=null){
                entity.setTitleEn(dto.getTitleEn());
            }else{
                entity.setTitleEn(optional.get().getTitleEn());
            }
            if(dto.getTitleRu()!=null){
                entity.setTitleRu(dto.getTitleRu());
            }else{
                entity.setTitleRu(optional.get().getTitleRu());
            }
            if(dto.getTitleUz()!=null){
                entity.setTitleUz(dto.getTitleUz());
            }else{
                entity.setTitleUz(optional.get().getTitleUz());
            }
            //Description
            if(dto.getDescriptionEn()!=null){
                entity.setDescriptionEn(dto.getDescriptionEn());
            }else{
                entity.setDescriptionEn(optional.get().getDescriptionEn());
            }
            if(dto.getDescriptionRu()!=null){
                entity.setDescriptionRu(dto.getDescriptionRu());
            }else{
                entity.setDescriptionRu(optional.get().getDescriptionRu());
            }
            if(dto.getDescriptionUz()!=null){
                entity.setDescriptionUz(dto.getDescriptionUz());
            }else{
                entity.setDescriptionUz(optional.get().getDescriptionUz());
            }
            if(dto.getCreatedDate()!=null){
                entity.setCreatedDate(LocalDate.parse(dto.getCreatedDate()));
            }else{
                entity.setCreatedDate(optional.get().getCreatedDate());
            }
            entity.setAttachId(optional.get().getAttachId());
            entity.setVisible(optional.get().getVisible());
            entity.setId(id);
            resultRepository.save(entity);
        }
    }

    public void updateAttach(String attachId,Integer id){
        Optional<ResultEntity> optional = resultRepository.findById(id);
        if(optional.isEmpty()){
            throw new ItemNotFoundException("Result is not found!!");
        }

        resultRepository.update(attachId,id);
    }
    public void delete(Integer id){
        Optional<ResultEntity> optional = resultRepository.findById(id);
        if(optional.isEmpty()){
            throw new ItemNotFoundException("Result is not found!!");
        }

        resultRepository.delete(id);
    }
    public void noDelete(Integer id){
        Optional<ResultEntity> optional = resultRepository.findById(id);
        if(optional.isEmpty()){
            throw new ItemNotFoundException("Result is not found!!");
        }

        resultRepository.noDelete(id);
    }

    public List<ResultDTO> top3(Lang lang){
        List<ResultEntity> resultEntities = resultRepository.top3();
        List<ResultDTO> resultDTOList=new LinkedList<>();
        for(int i=0;i<resultEntities.size();i++){
            ResultEntity entity = resultEntities.get(i);
            switch (lang){
                case en -> {
                    ResultDTO dto=new ResultDTO();
                    if(entity.getVisible()){
                        dto.setAttachId(entity.getAttachId());
                        dto.setCreatedDate(entity.getCreatedDate().toString());
                        dto.setNameEn(entity.getNameEn());
                        dto.setDescriptionEn(entity.getDescriptionEn());
                        dto.setTitleEn(entity.getTitleEn());
                        resultDTOList.add(dto);
                    }
                }
                case ru -> {
                    ResultDTO dto=new ResultDTO();
                    if(entity.getVisible()){
                        dto.setAttachId(entity.getAttachId());
                        dto.setCreatedDate(entity.getCreatedDate().toString());
                        dto.setNameRu(entity.getNameRu());
                        dto.setDescriptionRu(entity.getDescriptionRu());
                        dto.setTitleRu(entity.getTitleRu());
                        resultDTOList.add(dto);
                    }
                }
                case uz -> {
                    ResultDTO dto=new ResultDTO();
                    if(entity.getVisible()){
                        dto.setAttachId(entity.getAttachId());
                        dto.setCreatedDate(entity.getCreatedDate().toString());
                        dto.setNameUz(entity.getNameUz());
                        dto.setDescriptionUz(entity.getDescriptionUz());
                        dto.setTitleUz(entity.getTitleUz());
                        resultDTOList.add(dto);
                    }
                }
            }

        }
        return resultDTOList;
    }

    public PageImpl<ResultDTO> more(Lang lang, Integer size , Integer page){
        PageRequest paging = PageRequest.of(page, size);
        Page<ResultEntity> entityPage = resultRepository.findAll(paging);

        List<ResultDTO> dtoList = new ArrayList<>();
        for (ResultEntity entity: entityPage) {
            ResultDTO resultDTO = new ResultDTO();
                    switch (lang){
                        case ru -> {
                            resultDTO.setNameRu(entity.getNameRu());
                            resultDTO.setTitleRu(entity.getTitleRu());
                            resultDTO.setDescriptionRu(entity.getDescriptionRu());
                            resultDTO.setCreatedDate(entity.getCreatedDate().toString());
                            resultDTO.setAttachId(entity.getAttachId());
                        }
                        case uz -> {
                            resultDTO.setNameUz(entity.getNameUz());
                            resultDTO.setTitleUz(entity.getTitleUz());
                            resultDTO.setDescriptionUz(entity.getDescriptionUz());
                            resultDTO.setCreatedDate(entity.getCreatedDate().toString());
                            resultDTO.setAttachId(entity.getAttachId());
                        }
                        case en -> {
                            resultDTO.setNameEn(entity.getNameEn());
                            resultDTO.setTitleEn(entity.getTitleEn());
                            resultDTO.setDescriptionEn(entity.getDescriptionEn());
                            resultDTO.setCreatedDate(entity.getCreatedDate().toString());
                            resultDTO.setAttachId(entity.getAttachId());
                        }
                    }

            dtoList.add(resultDTO);
        }

        return new PageImpl<>(dtoList, PageRequest.of(page, size), entityPage.getTotalElements());
    }


    public  ResultDTO byId(Integer id,Lang lang){
        Optional<ResultEntity> byId = resultRepository.findById(id);
        if(byId.isEmpty()){
            throw new ItemNotFoundException("This Result is not found!!");
        }
        ResultDTO dto=new ResultDTO();
        switch (lang){
            case ru -> {

                dto.setId(id);
                dto.setNameRu(byId.get().getNameRu());
                dto.setTitleRu(byId.get().getTitleRu());
                dto.setDescriptionRu(byId.get().getDescriptionRu());
                dto.setAttachId(byId.get().getAttachId());
                dto.setCreatedDate(byId.get().getCreatedDate().toString());

            }
            case uz -> {
                dto.setId(id);
                dto.setNameUz(byId.get().getNameUz());
                dto.setDescriptionUz(byId.get().getDescriptionUz());
                dto.setTitleUz(byId.get().getTitleUz());
                dto.setAttachId(byId.get().getAttachId());
                dto.setCreatedDate(byId.get().getCreatedDate().toString());

            }
            case en -> {
                dto.setId(id);
                dto.setNameEn(byId.get().getNameEn());
                dto.setDescriptionEn(byId.get().getDescriptionEn());
                dto.setTitleEn(byId.get().getTitleEn());
                dto.setAttachId(byId.get().getAttachId());
                dto.setCreatedDate(byId.get().getCreatedDate().toString());

            }
        }
        return dto;
    }

}
