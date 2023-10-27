package com.example.repository;

import com.example.entity.NewsEntity;
import com.example.entity.ResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ResultRepository extends JpaRepository<ResultEntity,Integer> {

    @Transactional
    @Modifying
    @Query("update PartnerEntity r set r.attachId=?1 where r.id=?2")
    Integer update(String uid,Integer id);

    @Transactional
    @Modifying
    @Query("update PartnerEntity r set r.visible=false where r.id=?1")
    Integer delete(Integer id);

    @Transactional
    @Modifying
    @Query("update PartnerEntity r set r.visible=true where r.id=?1")
    Integer noDelete(Integer id);

    @Query(value = "SELECT  * FROM result ORDER BY created_date DESC LIMIT 3;",nativeQuery = true)
    List<ResultEntity> top3();

}
