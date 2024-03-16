package com.example.learned.service;

import com.example.learned.dao.LevelRepository;
import com.example.learned.entity.LevelEntity;
import com.example.learned.mapper.LevelMapper;
import com.example.learned.mapper.RoomMapper;
import com.example.learned.model.response.LevelRoomResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LevelService {
    private final LevelRepository levelRepository;
    private final LevelMapper levelMapper;
    private final RoomMapper roomMapper;
    public List<LevelRoomResponseDto> getAllLevelAndRooms(){
        List<LevelEntity> levelEntities = levelRepository.findAll();
        return levelEntities.stream()
                .map(levelEntity -> {
                    LevelRoomResponseDto levelRoomResponseDto = levelMapper.mapEntityToLevelRoomDto(levelEntity);
                    levelRoomResponseDto.setRoomEntities(roomMapper.mapEntityToResponseDtos(levelEntity.getRoomEntities()));
                    return levelRoomResponseDto;
                })
                .collect(Collectors.toList());
    }
}
