package com.example.learned.mapper;

import com.example.learned.entity.RoleEntity;
import com.example.learned.entity.RoomEntity;
import com.example.learned.model.auth.RoleDto;
import com.example.learned.model.request.CreateRoomDto;
import com.example.learned.model.response.RoomResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    @Mapping(target = "topicName", source = "roomEntity.topic.name")
    RoomResponseDto mapEntityToResponseDto(RoomEntity roomEntity);

    List<RoomResponseDto> mapEntityToResponseDtos(List<RoomEntity> roomEntities);

    @Mapping(target = "topic.id", source = "topicId")
    @Mapping(target = "category.id", source = "categoryId")
    @Mapping(target = "level.id", source = "levelId")
    RoomEntity mapRequestDtoToEntity(CreateRoomDto createRoomDto);

}
