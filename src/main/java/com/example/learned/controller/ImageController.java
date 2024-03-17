package com.example.learned.controller;

import com.example.learned.model.DataResult;
import com.example.learned.model.request.ImageSaveDto;
import com.example.learned.service.authservice.JwtService;
import com.example.learned.service.authservice.ImageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/v1/image")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;
    private final JwtService jwtService;
    @PostMapping(value = "/saveImage")
    public ResponseEntity<DataResult<?>> saveImage(HttpServletRequest request, @RequestBody ImageSaveDto imageSaveDto) {
        try {
            String token = request.getHeader("Authorization");
            if (token == null || !token.startsWith("Bearer ")) {
                throw new IllegalArgumentException("Invalid token");
            }
            Long userId = jwtService.extractUserIdFromAccessToken(token.replace("Bearer ", ""), true);
            imageService.saveImage(imageSaveDto.getImageData(), userId);
            return ResponseEntity.ok(new DataResult<>("Image saved successfully", HttpStatus.OK.value(), null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new DataResult<>("Invalid token", HttpStatus.UNAUTHORIZED.value(), null));
        } catch (NoSuchElementException | NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DataResult<>("User not found", HttpStatus.NOT_FOUND.value(), null));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DataResult<>("Error occurred while saving image", HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }

    @GetMapping("/showImage")
    public ResponseEntity<DataResult<?>> showImage(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            if (token == null || !token.startsWith("Bearer ")) {
                throw new IllegalArgumentException("Invalid token");
            }
            Long userId = jwtService.extractUserIdFromAccessToken(token.replace("Bearer ", ""), true);
            String imageData = imageService.showImage(userId);
            if (imageData.equals("null")){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DataResult<>("Image not found", HttpStatus.NOT_FOUND.value(), null));
            }
            return ResponseEntity.ok(new DataResult<>("Image shown successfully", HttpStatus.OK.value(), imageData));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new DataResult<>("Invalid token", HttpStatus.UNAUTHORIZED.value(), null));
        } catch (NoSuchElementException | NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DataResult<>("User not found", HttpStatus.NOT_FOUND.value(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DataResult<>("Error occurred while processing image", HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }



//    @PostMapping(value = "/uploadImage")
//    public ResponseEntity<?> uploadImage(HttpServletRequest request, @RequestParam("image") MultipartFile file) throws IOException {
//        if (file.isEmpty()) throw new FileNotFoundException("You should provide a valid file");
//
//        String token = request.getHeader("Authorization");
//        if (token == null || !token.startsWith("Bearer ")) {
//            throw new IllegalArgumentException("Invalid token");
//        }
//
//        Long userId = jwtService.extractUserIdFromAccessToken(token.replace("Bearer ", ""),true);
//        String uploadImage = imageService.uploadImage(file, userId);
//        return ResponseEntity.status(HttpStatus.CREATED).body(uploadImage);
//    }
//    @GetMapping("/{fileName}")
//    public ResponseEntity<?> downloadImage(@PathVariable String fileName) {
//        byte[] imageData = imageService.downloadImage(fileName);
//        return ResponseEntity.status(HttpStatus.OK)
//                .contentType(MediaType.valueOf(IMAGE_PNG_VALUE))
//                .body(imageData);
//    }
//    @GetMapping
//    public List<ImageEntity> getAllImage(){
//        return imageRepository.findAll();
//    }

}
