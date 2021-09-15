package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.repository.SubscribeRepository;
import com.cos.photogramstart.repository.UserRepository;
import com.cos.photogramstart.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${file.path}")
    private String uploadFolder;

    @Transactional(readOnly = true)
    public UserProfileDto userProfile(int pageUserId, int principalId) {
        User userEntity = userRepository.findById(pageUserId).orElseThrow(() -> {
            throw new CustomException("해당 프로필 페이지는 없는 페이지입니다");
        });
        userEntity.getImages().forEach((image)->{
            image.setLikeCount(image.getLikes().size());
        });
        int subscribeState = subscribeRepository.mSubscribeState(principalId, pageUserId);
        int subscribeCount = subscribeRepository.mSubscribeCount(pageUserId);
        return UserProfileDto.builder()
                .user(userEntity)
                .pageOwnerState(pageUserId == principalId)
                .imageCount(userEntity.getImages().size())
                .subscribeCount(subscribeCount)
                .subscribeState(subscribeState == 1)
                .build();
    }


    public User update(int id, User user) {
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);


        User userEntity = userRepository.findById(id).orElseThrow(() -> new CustomValidationApiException("찾을 수 없는 id입니다"));
        userEntity.setName(user.getName());
        userEntity.setPassword(encPassword);
        userEntity.setBio(user.getBio());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setBio(user.getBio());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());
        return userEntity;
    }

    public User updateProfileImage(int principalId, MultipartFile profileImageFile) {
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_"+ profileImageFile.getOriginalFilename();
        Path imageFilePath = Paths.get(uploadFolder + imageFileName);
        try {
            Files.write(imageFilePath, profileImageFile.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        User userEntity = userRepository.findById(principalId).orElseThrow(() -> {
            throw new CustomApiException("유저를 찾을 수 없습니다.");
        });
        userEntity.setProfileImageUrl(imageFileName);
        return userEntity;
    }
}
