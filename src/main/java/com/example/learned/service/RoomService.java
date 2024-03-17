package com.example.learned.service;

import com.example.learned.dao.RoomRepository;
import com.example.learned.entity.RoomEntity;
import com.example.learned.mapper.RoomMapper;
import com.example.learned.model.request.CreateRoomDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomMapper roomMapper;
    private final RoomRepository roomRepository;
    @Transactional
    public void createRoom(CreateRoomDto createRoomDto, String nickname){
        RoomEntity roomEntity = roomMapper.mapRequestDtoToEntity(createRoomDto);
        roomEntity.setNickname(nickname);
        roomRepository.save(roomEntity);
    }
}
