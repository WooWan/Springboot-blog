package com.cos.photogramstart.web.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CMRespDto<T> {

    private int code;
    private String message;
    private T data;
}
