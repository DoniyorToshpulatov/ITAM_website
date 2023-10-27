package com.example.repository;

import com.example.entity.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PartnerRepository extends JpaRepository<PartnerEntity,Integer> {

    @Transactional
    @Modifying
    @Query("update PartnerEntity part set part.attachId=?1 where part.id=?2")
    Integer update(String uid,Integer id);

    @Transactional
    @Modifying
    @Query("update PartnerEntity part set part.visible=false where part.id=?1")
    Integer delete(Integer id);

    @Transactional
    @Modifying
    @Query("update PartnerEntity part set part.visible=true where part.id=?1")
    Integer noDelete(Integer id);

}
