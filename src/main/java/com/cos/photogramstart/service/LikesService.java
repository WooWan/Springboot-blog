package com.cos.photogramstart.service;

import com.cos.photogramstart.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikesService {

    private final LikesRepository likesRepository;

    public void addLike(int imageId, Long principalId) {
        likesRepository.mLikes(imageId, principalId);
    }

    public void deleteLike(int imageId, Long principalId) {
        likesRepository.mUnLikes(imageId, principalId);
    }
}
