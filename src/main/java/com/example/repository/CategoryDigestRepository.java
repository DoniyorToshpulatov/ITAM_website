package com.example.repository;

import com.example.entity.CategoryDigestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CategoryDigestRepository extends JpaRepository<CategoryDigestEntity,Integer> {

    @Transactional
    @Modifying
    @Query("update CategoryDigestEntity cat set cat.visible=false where cat.id=?1")
    Integer delete(Integer  id);

    @Transactional
    @Modifying
    @Query("update CategoryDigestEntity cat set cat.visible=true where cat.id=?1")
    Integer noDelete(Integer  id);

    @Transactional
    @Modifying
    @Query("update CategoryDigestEntity cat set cat.attachId=?1 where cat.id=?2")
    Integer update(String uid,Integer  id);
}
