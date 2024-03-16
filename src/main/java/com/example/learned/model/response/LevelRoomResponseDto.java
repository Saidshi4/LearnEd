package com.example.learned.model.response;

import com.example.learned.entity.RoomEntity;
import com.example.learned.entity.enums.EUserStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LevelRoomResponseDto {
    Long id;
    String name;
    List<RoomResponseDto> roomEntities;
}
