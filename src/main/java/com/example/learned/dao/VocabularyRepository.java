package com.example.learned.dao;

import com.example.learned.entity.LevelEntity;
import com.example.learned.entity.VocabularyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VocabularyRepository extends JpaRepository<VocabularyEntity,Long> {
   @Query(value = "select * from vocalbularies where topic_id=:topicId and level_id=:levelId",nativeQuery = true)
   List<VocabularyEntity> findByTopicIdAndLevelId(Long topicId, Long levelId);
}
