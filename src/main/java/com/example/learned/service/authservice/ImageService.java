package com.example.learned.service.authservice;

import com.example.learned.dao.ImageRepository;
import com.example.learned.dao.UserRepository;
import com.example.learned.entity.ImageEntity;
import com.example.learned.entity.UserEntity;
import com.example.learned.mapper.ImageMapper;
import com.example.learned.model.ImageDto;
import com.example.learned.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;
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

    @Transactional
    public String uploadImage(MultipartFile imageFile,Long userId) throws IOException {
        var imageToSave = ImageDto.builder()
                .name(imageFile.getOriginalFilename())
                .type(imageFile.getContentType())
                .imageData(ImageUtil.compressImage(imageFile.getBytes()))
                .userId(userId)
                .build();

        imageRepository.save(imageMapper.dtoToEntity(imageToSave));

        return "file uploaded successfully : " + imageFile.getOriginalFilename();
    }

    @Transactional(readOnly = true)
    public byte[] downloadImage(String imageName) {
        Optional<ImageEntity> dbImage = imageRepository.findByName(imageName);
        return dbImage.map(image -> {
            try {
                return ImageUtil.decompressImage(image.getImageData());
            } catch (DataFormatException | IOException exception) {
                throw new ContextedRuntimeException("Error downloading an image", exception)
                        .addContextValue("Image ID",  image.getId())
                        .addContextValue("Image name", imageName);
            }
        }).orElse(null);
    }

}
