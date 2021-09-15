package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.comment.Comment;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.repository.CommentRepository;
import com.cos.photogramstart.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public Comment addComment(String content, int imageId, int userId) {
        Comment comment = new Comment();

        Image image = new Image();
        image.setId(imageId);

        User userEntity = userRepository.findById(userId)
                .orElseThrow(() -> {
                    throw new CustomApiException("존재하지 않는 회원입니다.");
                });

        comment.setContent(content);
        comment.setUser(userEntity);
        comment.setImage(image);

        return commentRepository.save(comment);
    }

    public void deleteComment(int id) {
        commentRepository.deleteById(id);
    }

}
