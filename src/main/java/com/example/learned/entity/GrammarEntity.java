package com.example.learned.entity;


import com.example.learned.entity.enums.ERole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity(name="grammars")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GrammarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String definition;
    String example;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="level_id",referencedColumnName = "id")
    LevelEntity level;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="topic_id",referencedColumnName = "id")
    TopicEntity topic;

}