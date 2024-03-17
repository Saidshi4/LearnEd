package com.example.learned.service;

import com.example.learned.dao.LevelRepository;
import com.example.learned.dao.TopicRepository;
import com.example.learned.entity.LevelEntity;
import com.example.learned.entity.TopicEntity;
import com.example.learned.entity.UserEntity;
import com.example.learned.mapper.LevelMapper;
import com.example.learned.mapper.RoomMapper;
import com.example.learned.mapper.TopicMapper;
import com.example.learned.model.response.LevelRoomResponseDto;
import com.example.learned.model.response.TopicResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicService {
    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;
    public List<TopicResponseDto> getAllTopicByCategegory(Long categoryId, int pageNo, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by("id").descending());
        Page<TopicEntity> pagingTopic = topicRepository.getTopicEntitiesByCategory_Id(categoryId,  pageRequest);
        return topicMapper.mapEntityToTopicResponses(pagingTopic.getContent());

    }
}
