package com.example.learned.controller;

import com.example.learned.dao.RoomRepository;
import com.example.learned.mapper.RoomMapper;
import com.example.learned.model.response.LevelRoomResponseDto;
import com.example.learned.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/api/level")
@RequiredArgsConstructor
public class LevelController {
    private final LevelService levelService;
    private final RoomMapper roomMapper;
    private final RoomRepository roomRepository;
    @GetMapping("/getAllRoomsWithLevel")
    public List<LevelRoomResponseDto> getAll(){
        return levelService.getAllLevelAndRooms();
    }


}
