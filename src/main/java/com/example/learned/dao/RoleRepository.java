package com.example.learned.dao;

import com.example.learned.entity.RoleEntity;
import com.example.learned.entity.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
    @Query(value = "select r from roles r where r.name = ?1", nativeQuery = false)
    RoleEntity findByName(ERole name);

}
