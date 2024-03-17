package com.example.learned.model.response;

import com.example.learned.entity.CategoryEntity;
import com.example.learned.entity.LevelEntity;
import com.example.learned.entity.RoomUserEntity;
import com.example.learned.entity.TopicEntity;
import com.example.learned.entity.enums.EUserStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level= AccessLevel.PRIVATE)
public class RoomResponseDto {
    Long id;
    LocalDateTime createdAt;
    Integer participantCount;
    Boolean pub;
    String topicName;
    String nickname;
}
