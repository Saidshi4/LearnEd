package com.example.learned.service.authservice;

import com.example.learned.dao.UserRepository;
import com.example.learned.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final UserRepository userRepository;
    public void saveImage(String imageData,Long userId) throws IOException{
        byte[] image=Base64.getDecoder().decode(imageData);

        UserEntity user=userRepository.findById(userId).orElse(null);
        Objects.requireNonNull(user).setImageData(image);
        userRepository.save(user);
    }

    public String showImage(Long userId){
        UserEntity user = userRepository.findById(userId).orElseThrow(null);
        return Base64.getEncoder().encodeToString(user.getImageData());
    }



}
