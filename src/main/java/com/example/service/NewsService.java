package com.example.service;

import com.example.dto.NewsDTO;
import com.example.dto.ResultDTO;
import com.example.entity.NewsEntity;
import com.example.entity.ResultEntity;
import com.example.enums.Lang;
import com.example.exp.ItemNotFoundException;
import com.example.exp.NewsFieldException;
import com.example.repository.NewsRepository;
import com.example.validation.NewsValidation;
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
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;

    public NewsEntity create(NewsDTO dto) {
        NewsValidation.isValid(dto);
        NewsEntity entity = toEntity(dto);
        newsRepository.save(entity);
        return null;
    }

    public NewsEntity toEntity(NewsDTO dto) {
        NewsEntity entity = new NewsEntity();
        entity.setId(dto.getId());
        entity.setAttachId(dto.getAttachId());
        entity.setNameEn(dto.getNameEn());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setLongEn(dto.getLongEn());
        entity.setLongUz(dto.getLongUz());
        entity.setLongRu(dto.getLongRu());
        entity.setShortRu(dto.getShortRu());
        entity.setShortUz(dto.getShortUz());
        entity.setShortEn(dto.getShortEn());
        entity.setCreatedDate(LocalDate.parse(dto.getCreatedDate()));
        return entity;
    }

    public void update(NewsDTO dto, Integer id) {
        Optional<NewsEntity> optional = newsRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("News is not found!!");
        }
        NewsEntity entity = new NewsEntity();
        entity.setId(optional.get().getId());
        // ## name##
        if (dto.getNameUz() != null && dto.getNameUz().trim().length() > 8) {
            entity.setNameUz(dto.getNameUz());
        } else {
            entity.setNameUz(optional.get().getNameUz());
        }

        if (dto.getNameEn() != null && dto.getNameEn().trim().length() > 8) {
            entity.setNameEn(dto.getNameEn());
        } else {
            entity.setNameEn(optional.get().getNameEn());
        }

        if (dto.getNameRu() != null && dto.getNameRu().trim().length() > 8) {
            entity.setNameRu(dto.getNameRu());
        } else {
            entity.setNameRu(optional.get().getNameRu());
        }
// ##    ##
//## long ##
        if (dto.getLongUz() != null && dto.getLongUz().trim().length() > 8) {
            entity.setLongUz(dto.getLongUz());
        } else {
            entity.setLongUz(optional.get().getLongUz());
        }
        if (dto.getLongRu() != null && dto.getLongRu().trim().length() > 8) {
            entity.setLongRu(dto.getLongUz());
        } else {
            entity.setLongRu(optional.get().getLongRu());
        }
        if (dto.getLongEn() != null && dto.getLongEn().trim().length() > 8) {
            entity.setLongEn(dto.getLongEn());
        } else {
            entity.setLongEn(optional.get().getLongEn());
        }
        // ##  ##

        // ## createdDate ##
        if (dto.getCreatedDate() != null && dto.getCreatedDate().trim().length() > 8) {
            entity.setCreatedDate(LocalDate.parse(dto.getCreatedDate()));
        } else {
            entity.setCreatedDate(optional.get().getCreatedDate());
        }
        entity.setAttachId(optional.get().getAttachId());
        entity.setVisible(optional.get().getVisible());
        newsRepository.save(entity);
    }

    public void delete(Integer id) {
        Optional<NewsEntity> optional = newsRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("News is not found!!");
        }
        newsRepository.delete(id);
    }

    public void updateAttachId(Integer id, String attachId) {
        Optional<NewsEntity> byId = newsRepository.findById(id);
        if (byId.isEmpty()) {
            throw new ItemNotFoundException("News is not found!! ");
        }
        newsRepository.update(attachId, id);
    }

    public List<NewsDTO> top3(Lang lang) {
        List<NewsEntity> newsEntityList = newsRepository.top3();
        List<NewsDTO> newsDTOList = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            NewsEntity entity = newsEntityList.get(i);
            switch (lang) {
                case uz -> {
                    NewsDTO newsdto = toDTOUz(entity);
                    if (newsdto != null) {
                        newsDTOList.add(newsdto);
                    }
                }
                case en -> {
                    NewsDTO newsDTO = toDTOEn(entity);
                    if (newsDTO != null) {
                        newsDTOList.add(newsDTO);
                    }
                }
                case ru -> {
                    NewsDTO newsDTO = toDTORu(entity);
                    if (newsDTO != null) {
                        newsDTOList.add(newsDTO);
                    }
                }
                default -> System.out.println("Bunaqasi yuq");
            }

        }
        if (newsDTOList.isEmpty()) {
            throw new NewsFieldException("Yangilik yuqku bronta ham");
        }
        return newsDTOList;
    }

    public List<NewsDTO> more(Lang lang) {
        List<NewsEntity> entityList = newsRepository.findAllByOrderById();
        if (entityList.isEmpty()) {
            throw new NewsFieldException("News list is empty!!");
        }
        List<NewsDTO> dtoList = new LinkedList<>();
        for (int i = 0; i < entityList.size(); i++) {
            NewsEntity entity = entityList.get(i);
            switch (lang) {
                case uz -> {
                    NewsDTO dto = toDTOUz(entity);
                    if (dto != null) {
                        dtoList.add(dto);
                    }
                }
                case ru -> {
                    NewsDTO dto = toDTORu(entity);
                    if (dto != null) {
                        dtoList.add(dto);
                    }
                }
                case en -> {
                    NewsDTO dto = toDTOEn(entity);
                    if (dto != null) {
                        dtoList.add(dto);
                    }
                }
                default -> System.out.println("Bunaqasi yuqkuu!!");
            }
        }
        if (!dtoList.isEmpty()) {
            return dtoList;
        }
        return null;

    }

    public PageImpl<NewsDTO> morePage(Integer page,Integer size,Lang lang){
        PageRequest paging = PageRequest.of(page, size);
        Page<NewsEntity> entityPage = newsRepository.findAll(paging);
        long totalElements = entityPage.getTotalElements();
        List<NewsDTO> dtoList = new ArrayList<>();
        switch (lang){
            case uz -> {
                for (NewsEntity entity: entityPage) {
                    NewsDTO dto=toDTOUz(entity);
                    dtoList.add(dto);
                }
            }
            case ru -> {
                for (NewsEntity entity: entityPage) {
                    NewsDTO dto=toDTORu(entity);
                    dtoList.add(dto);
                }
            }
            case en -> {
                for (NewsEntity entity: entityPage) {
                    NewsDTO dto=toDTOEn(entity);
                    dtoList.add(dto);
                }
            }
        }
        return new PageImpl<>(dtoList, paging, totalElements);

    }
    public  List<NewsDTO> moreAdmin(){
        List<NewsEntity> entityList=newsRepository.findAllByOrderById();
        if(entityList.isEmpty()){
            throw new NewsFieldException("Is empty!!");
        }
        List<NewsDTO> dtoList=new LinkedList<>();
        for(int i=0;i<entityList.size();i++){
            NewsEntity entity=entityList.get(i);
            NewsDTO dto=toDto(entity);
            dtoList.add(dto);
        }
        if(!dtoList.isEmpty()){
            return  dtoList;
        }
        return null;
    }

    public void noDelete(Integer id){
        Optional<NewsEntity> entity=newsRepository.findById(id);
        if(entity.isEmpty()){
            throw new ItemNotFoundException("News is not found!!");
        }
        newsRepository.noDelete(id);
    }
    public NewsDTO toDTOUz(NewsEntity entity) {
        if (entity.getVisible().equals(true)) {
            NewsDTO dto = new NewsDTO();
            dto.setId(entity.getId());
            dto.setNameUz(entity.getNameUz());
            dto.setShortUz(entity.getShortUz());
            dto.setLongUz(entity.getLongUz());
            dto.setCreatedDate(entity.getCreatedDate().toString());
            dto.setAttachId(entity.getAttachId());
            return dto;
        }
        return null;
    }

    public NewsDTO toDTORu(NewsEntity entity) {
        if (entity.getVisible().equals(true)) {
            NewsDTO dto = new NewsDTO();
            dto.setId(entity.getId());
            dto.setNameRu(entity.getNameRu());
            dto.setShortRu(entity.getShortRu());
            dto.setLongRu(entity.getLongRu());
            dto.setCreatedDate(entity.getCreatedDate().toString());
            dto.setAttachId(entity.getAttachId());

            return dto;
        }


        return null;
    }

    public NewsDTO toDTOEn(NewsEntity entity) {
        if (entity.getVisible().equals(true)) {
            NewsDTO dto = new NewsDTO();
            dto.setId(entity.getId());
            dto.setNameEn(entity.getNameEn());
            dto.setShortEn(entity.getShortEn());
            dto.setLongEn(entity.getLongEn());
            dto.setCreatedDate(entity.getCreatedDate().toString());
            dto.setAttachId(entity.getAttachId());
            return dto;
        }


        return null;
    }

    public NewsDTO toDto(NewsEntity entity) {
            NewsDTO dto = new NewsDTO();
            dto.setId(entity.getId());
            dto.setNameUz(entity.getNameUz());
            dto.setNameEn(entity.getNameEn());
            dto.setNameRu(entity.getNameRu());
            dto.setShortEn(entity.getShortEn());
            dto.setShortUz(entity.getShortUz());
            dto.setShortRu(entity.getShortRu());
            dto.setLongEn(entity.getLongEn());
            dto.setLongRu(entity.getLongRu());
            dto.setLongUz(entity.getLongUz());
            dto.setVisible(entity.getVisible());
            dto.setCreatedDate(entity.getCreatedDate().toString());
            dto.setAttachId(entity.getAttachId());
            return dto;
    }
}
