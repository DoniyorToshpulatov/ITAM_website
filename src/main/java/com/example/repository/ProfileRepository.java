package com.example.repository;

import com.example.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer>, CrudRepository<ProfileEntity,Integer> {
    ProfileEntity findByEmail(String email);
   Optional <ProfileEntity> findByEmailAndPassword(String email, String password);

    @Transactional
    @Modifying
    @Query("update ProfileEntity p set p.attachId=?1 where p.id=?2")
    Integer update(String uid,Integer id);


    @Query("select p from ProfileEntity  p where p.deportmentId=?1")
    List<ProfileEntity>  getByDeportment(Integer id);

    @Query("select p from ProfileEntity p where p.workRole=?1")
    ProfileEntity findDirector(String str);

    @Query("select  p  from ProfileEntity  p  where p.workRole=?1")
    List<ProfileEntity> getZam(String zam);
}
