package com.cos.photogramstart.web.dto.subscribe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class SubscribeDto {
    private Long userId;
    private String username;
    private String profileImageUrl;
    private Integer subscribeState;
    private Integer equalUserState;
}
