package com.example.learned.mapper;

import com.example.learned.entity.LevelEntity;
import com.example.learned.entity.TopicEntity;
import com.example.learned.model.response.LevelRoomResponseDto;
import com.example.learned.model.response.TopicResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TopicMapper {
    TopicResponseDto mapEntityToTopicResponse(TopicEntity topicEntity);
    List<TopicResponseDto> mapEntityToTopicResponses(List<TopicEntity> topicEntity);
}
