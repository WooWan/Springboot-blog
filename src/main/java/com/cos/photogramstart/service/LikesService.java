package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.repository.ImageRepository;
import com.cos.photogramstart.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LikesService {

    private final LikesRepository likesRepository;
    private final ImageRepository imageRepository;

    public void addLike(int imageId, int principalId) {
        Image findImage = imageRepository.findById(imageId).orElseThrow(() -> {
            throw new CustomException("해당 이미지는 존재하지 않습니다.");
        });
        findImage.setLikeCount(findImage.getLikeCount()+1);
        log.info("like count :{}", findImage.getLikeCount());
        likesRepository.mLikes(imageId, principalId);
    }

    public void deleteLike(int imageId, int principalId) {
        likesRepository.mUnLikes(imageId, principalId);
    }
}
