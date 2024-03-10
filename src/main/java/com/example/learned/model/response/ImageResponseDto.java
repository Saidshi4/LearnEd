package com.example.learned.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponseDto {
    private Long id;
    private String name;
    private String type;
    private String imageData;
}
