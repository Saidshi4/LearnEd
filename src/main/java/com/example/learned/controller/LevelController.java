package com.example.learned.controller;

import com.example.learned.dao.RoomRepository;
import com.example.learned.mapper.RoomMapper;
import com.example.learned.model.DataResult;
import com.example.learned.model.response.LevelResponseDto;
import com.example.learned.model.response.LevelRoomResponseDto;
import com.example.learned.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/api/level")
@RequiredArgsConstructor
public class LevelController {
    private final LevelService levelService;
    @GetMapping("/getAllRoomsWithLevel")
    public ResponseEntity<DataResult<List<LevelRoomResponseDto>>> getAllRoomWithLevel(){
        return ResponseEntity.ok(new DataResult<>("Getting rooms with level successfully",
                HttpStatus.OK.value(), levelService.getAllLevelAndRooms()));
    }
    @GetMapping("/getAllLevel")
    public ResponseEntity<DataResult<List<LevelResponseDto>>> getAllLevel(){
        return ResponseEntity.ok(new DataResult<>("Getting level successfully",
                HttpStatus.OK.value(), levelService.getAllLevel()));
    }


}
