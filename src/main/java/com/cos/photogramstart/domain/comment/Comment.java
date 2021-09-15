package com.cos.photogramstart.domain.comment;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(length = 100, nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "imageId")
    private Image image;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private LocalDateTime createDate;

    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
