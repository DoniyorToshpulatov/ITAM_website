package com.example.repository;


import com.example.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<NewsEntity,Integer>, CrudRepository<NewsEntity,Integer> {

    @Transactional
    @Modifying
    @Query("update NewsEntity n set n.visible=false where n.id=?1")
    Integer delete(Integer id);

    @Transactional
    @Modifying
    @Query("update NewsEntity n set n.visible=true where n.id=?1")
    Integer noDelete(Integer id);

    @Transactional
    @Modifying
    @Query("update NewsEntity n set n.attachId=?1 where n.id=?2")
    Integer update(String uid,Integer id);

    @Query(value = "SELECT  * FROM news ORDER BY created_date DESC LIMIT 3;",nativeQuery = true)
    List<NewsEntity> top3();

    List<NewsEntity> findAllByOrderById();

}
