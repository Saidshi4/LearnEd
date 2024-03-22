package com.example.learned.service;

import com.example.learned.dao.VocabularyRepository;
import com.example.learned.mapper.VocabularyMapper;
import com.example.learned.model.response.VocabularyResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VocabularyService {
    private final VocabularyRepository vocabularyRepository;
    private final VocabularyMapper vocabularyMapper;
    public List<VocabularyResponseDto> getAllVocabularies(Long topicId ,Long levelId){
        return vocabularyMapper.mapEntityToVocabularyResponseDtos(vocabularyRepository.findByTopicIdAndLevelId(topicId,levelId));
    }
}
