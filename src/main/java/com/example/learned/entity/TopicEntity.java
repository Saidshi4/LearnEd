package com.example.learned.entity;


import com.example.learned.entity.enums.ERole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity(name="topics")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level= AccessLevel.PRIVATE)
public class TopicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @OneToMany(mappedBy = "topic")
    List<GrammarEntity> grammarEntities;
    @OneToMany(mappedBy = "topic")
    List<VocabularyEntity> vocabularyEntities;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="category_id",referencedColumnName = "id")
    CategoryEntity category;
    @OneToMany(mappedBy = "topic")
    List<RoomEntity> roomEntities;

}