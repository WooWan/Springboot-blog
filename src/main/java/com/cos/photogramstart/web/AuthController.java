package com.cos.photogramstart.web;

import com.cos.photogramstart.web.dto.auth.SignupDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class AuthController {

    @GetMapping("/auth/signin")
    public String signinForm() {
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm() {
        return "auth/signup";
    }
    @PostMapping("/auth/signup")
    public String signup(SignupDto signupDto) {
        System.out.println("signup");
        log.info(signupDto.toString());
        log.info(signupDto.getUsername());

        return "auth/signup";
    }
}
