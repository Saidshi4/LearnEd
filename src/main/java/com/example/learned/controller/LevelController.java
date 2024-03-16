package com.example.learned.controller;

import com.example.learned.dao.ImageRepository;
import com.example.learned.dao.RoomRepository;
import com.example.learned.mapper.RoomMapper;
import com.example.learned.model.DataResult;
import com.example.learned.model.request.ImageSaveDto;
import com.example.learned.model.response.LevelRoomResponseDto;
import com.example.learned.model.response.RoomResponseDto;
import com.example.learned.service.LevelService;
import com.example.learned.service.authservice.ImageService;
import com.example.learned.service.authservice.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/v1/api/level")
@RequiredArgsConstructor
public class LevelController {
    private final LevelService levelService;
    private final RoomMapper roomMapper;
    private final RoomRepository roomRepository;
    @GetMapping
    public List<LevelRoomResponseDto> getAll(){
        return levelService.getAllLevelAndRooms();
    }
    @GetMapping("/sa")
    public List<RoomResponseDto> getAll1(){
        return roomMapper.mapEntityToResponseDtos(roomRepository.findAll());
    }


}
