package com.example.repository;

import com.example.entity.DeportmentEntity;
import jdk.jfr.TransitionTo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface DeportmentRepository extends JpaRepository<DeportmentEntity,Integer> {

    @Transactional
    @Modifying
    @Query("update DeportmentEntity d set d.attachId=?1 where d.id=?2")
    Integer update(String uid,Integer id);

    @Transactional
    @Modifying
    @Query("update DeportmentEntity d set d.visible=false where d.id=?1")
    Integer delete(Integer id);

    @Transactional
    @Modifying
    @Query("update DeportmentEntity d set d.visible=true where d.id=?1")
    Integer noDelete(Integer id);


}
