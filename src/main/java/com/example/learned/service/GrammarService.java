package com.example.learned.service;

import com.example.learned.dao.GrammarRepository;
import com.example.learned.dao.TopicRepository;
import com.example.learned.dao.VocabularyRepository;
import com.example.learned.entity.TopicEntity;
import com.example.learned.mapper.GrammarMapper;
import com.example.learned.mapper.TopicMapper;
import com.example.learned.mapper.VocabularyMapper;
import com.example.learned.model.response.GrammarResponseDto;
import com.example.learned.model.response.TopicResponseDto;
import com.example.learned.model.response.VocabularyResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GrammarService {
    private final GrammarRepository grammarRepository;
    private final GrammarMapper grammarMapper;
    public List<GrammarResponseDto> getAllGrammars(Long topicId , Long levelId){
        return grammarMapper.mapEntityToGrammarResponseDtos(grammarRepository.findByTopicIdAndLevelId(topicId,levelId));
    }
}
