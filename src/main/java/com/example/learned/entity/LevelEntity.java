package com.example.learned.entity;


import com.example.learned.entity.enums.ERole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity(name="levels")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level= AccessLevel.PRIVATE)
public class LevelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @OneToMany(mappedBy = "level")
    List<GrammarEntity> grammarEntities;
    @OneToMany(mappedBy = "level")
    List<VocabularyEntity> vocabularyEntities;
    @OneToMany(mappedBy = "level")
    List<RoomEntity> roomEntities;
}