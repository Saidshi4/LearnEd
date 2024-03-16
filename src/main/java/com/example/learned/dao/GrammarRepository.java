package com.example.learned.dao;

import com.example.learned.entity.GrammarEntity;
import com.example.learned.entity.LevelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrammarRepository extends JpaRepository<GrammarEntity,Long> {

}
