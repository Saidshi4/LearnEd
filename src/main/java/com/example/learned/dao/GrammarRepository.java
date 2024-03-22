package com.example.learned.dao;

import com.example.learned.entity.GrammarEntity;
import com.example.learned.entity.LevelEntity;
import com.example.learned.entity.VocabularyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrammarRepository extends JpaRepository<GrammarEntity,Long> {
    @Query(value = "select * from grammars where topic_id=:topicId and level_id=:levelId",nativeQuery = true)
    List<GrammarEntity> findByTopicIdAndLevelId(Long topicId, Long levelId);
}
