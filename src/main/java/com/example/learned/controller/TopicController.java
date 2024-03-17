package com.example.learned.controller;

import com.example.learned.dao.RoomRepository;
import com.example.learned.entity.TopicEntity;
import com.example.learned.entity.UserEntity;
import com.example.learned.mapper.RoomMapper;
import com.example.learned.model.response.LevelRoomResponseDto;
import com.example.learned.model.response.TopicResponseDto;
import com.example.learned.service.LevelService;
import com.example.learned.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/api/topic")
@RequiredArgsConstructor
public class TopicController {
    private final TopicService topicService;
    @GetMapping("/getTopicWithPaging/{categoryId}")
    public List<TopicResponseDto> getTopicWithPaging(@PathVariable Long categoryId, @RequestParam(defaultValue = "0") Integer pageNo,
                                                     @RequestParam(defaultValue = "10") Integer pageSize){

        return topicService.getAllTopicByCategegory(categoryId,pageNo,pageSize);

    }


}
