package com.example.learned.model.request;

import com.example.learned.entity.CategoryEntity;
import com.example.learned.entity.LevelEntity;
import com.example.learned.entity.RoomUserEntity;
import com.example.learned.entity.TopicEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateRoomDto {
    Long id;
    String password;
    Boolean pub;
    Integer participantCount;
    Long levelId;
    Long topicId;
    Long categoryId;
}
