package com.example.learned.dao;

import com.example.learned.entity.LevelEntity;
import com.example.learned.entity.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<TopicEntity,Long> {

}
