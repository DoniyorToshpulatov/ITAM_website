package com.example.repository;

import com.example.entity.DigestEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DigestRepository extends JpaRepository<DigestEntity,Integer> {
    @Transactional
    @Modifying
    @Query("update DigestEntity dig set dig.attachId=?1 where dig.id=?2")
    Integer update(String uid,Integer id);

    @Transactional
    @Modifying
    @Query("update DigestEntity did set did.visible=false where did.id=?1")
    Integer delete(Integer id);

    @Transactional
    @Modifying
    @Query("update DigestEntity did set did.visible=true where did.id=?1")
    Integer noDelete(Integer id);

    List<DigestEntity> findByCategoryId(Integer categoryId);

}
