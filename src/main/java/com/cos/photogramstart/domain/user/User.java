package com.cos.photogramstart.domain.user;

import com.cos.photogramstart.domain.image.Image;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, length = 100, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;

    private String website; //웹 사이트
    private String bio; // 자기소개

    private String phone;
    private String gender;


    private String profileImageUrl;
    private String role;

    //연관관계가 주인이 x, 테이블에 칼럼 만들지 않음
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Image> images;

    private LocalDateTime createDate;

    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
