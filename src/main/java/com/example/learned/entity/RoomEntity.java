package com.example.learned.entity;


import com.example.learned.entity.enums.ERole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name="rooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nickname;
    String password;
    @CreationTimestamp
    LocalDateTime createdAt;
    Integer participant;
    Boolean pub;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="level_id",referencedColumnName = "id")
    LevelEntity level;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="topic_id",referencedColumnName = "id")
    TopicEntity topic;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="category_id",referencedColumnName = "id")
    CategoryEntity category;
    @OneToMany(mappedBy = "room")
    List<RoomUserEntity> roomUserEntities;
}