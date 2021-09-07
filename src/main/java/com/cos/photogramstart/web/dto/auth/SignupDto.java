package com.cos.photogramstart.web.dto.auth;

import lombok.Getter;
import lombok.Setter;

//data transfer object
@Getter @Setter
public class SignupDto {
    private String username;
    private String password;
    private String email;
    private String name;
}
