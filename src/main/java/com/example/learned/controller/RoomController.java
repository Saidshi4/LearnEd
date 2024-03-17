package com.example.learned.controller;

import com.example.learned.dao.RoomRepository;
import com.example.learned.mapper.RoomMapper;
import com.example.learned.model.DataResult;
import com.example.learned.model.request.CreateRoomDto;
import com.example.learned.model.response.LevelRoomResponseDto;
import com.example.learned.service.LevelService;
import com.example.learned.service.RoomService;
import com.example.learned.service.authservice.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/api/room")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;
    private final JwtService jwtService;
    @PostMapping("/createRoom")
    public ResponseEntity<DataResult<?>> createRoom(HttpServletRequest request, @RequestBody CreateRoomDto createRoomDto){
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid token");
        }
        String nickname = jwtService.extractUserNicknameFromAccessToken(token.replace("Bearer ", ""), true);
        roomService.createRoom(createRoomDto, nickname);
        return ResponseEntity.
                ok(new DataResult<>("User registered successfully", HttpStatus.OK.value(), null));
    }



}
