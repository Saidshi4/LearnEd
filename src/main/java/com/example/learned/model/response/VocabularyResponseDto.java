package com.example.learned.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level= AccessLevel.PRIVATE)
public class VocabularyResponseDto {
    Long id;
    String name;
    String definition;
    String example;
}
