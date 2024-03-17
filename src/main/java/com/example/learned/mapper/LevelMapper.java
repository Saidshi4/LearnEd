package com.example.learned.mapper;

import com.example.learned.entity.LevelEntity;
import com.example.learned.entity.RoleEntity;
import com.example.learned.model.auth.RoleDto;
import com.example.learned.model.response.LevelResponseDto;
import com.example.learned.model.response.LevelRoomResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LevelMapper {
    LevelRoomResponseDto mapEntityToLevelRoomDto(LevelEntity levelEntity);
    List<LevelRoomResponseDto> mapEntityToLevelRoomDtos(List<LevelEntity> levelEntities);
    LevelResponseDto mapEntityToLevelResponseDto(LevelEntity levelEntity);
    List<LevelResponseDto> mapEntityToLevelResponseDtos(List<LevelEntity> levelEntities);
}
