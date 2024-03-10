package com.example.learned.controller;

import com.example.learned.dao.ImageRepository;
import com.example.learned.entity.ImageEntity;
import com.example.learned.service.authservice.JwtService;
import com.example.learned.service.authservice.ImageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;


@RestController
@RequestMapping("/v1/image")
@RequiredArgsConstructor
public class ImageController {
  private final ImageRepository imageRepository;
    private final ImageService imageService;
    private final JwtService jwtService;

    @PostMapping(value = "/uploadImage")

    public ResponseEntity<?> uploadImage(HttpServletRequest request, @RequestParam("image") MultipartFile file) throws IOException {
        if (file.isEmpty()) throw new FileNotFoundException("You should provide a valid file");

        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid token");
        }

        Long userId = jwtService.extractUserIdFromAccessToken(token.replace("Bearer ", ""),true);
        String uploadImage = imageService.uploadImage(file, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(uploadImage);
    }


    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) {
        byte[] imageData = imageService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(IMAGE_PNG_VALUE))
                .body(imageData);
    }
    @GetMapping
    public List<ImageEntity> getAllImage(){
        return imageRepository.findAll();
    }
}
