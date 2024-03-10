package com.example.learned.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "user_files")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String imageUrl;
    @Lob
    private byte[] imageData;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private UserEntity user;


}
