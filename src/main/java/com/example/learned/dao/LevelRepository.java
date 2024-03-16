package com.example.learned.dao;

import com.example.learned.entity.LevelEntity;
import com.example.learned.entity.RoleEntity;
import com.example.learned.entity.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<LevelEntity,Long> {

}
