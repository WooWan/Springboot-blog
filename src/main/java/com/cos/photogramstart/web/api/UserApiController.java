package com.cos.photogramstart.web.api;


import com.cos.photogramstart.config.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.SubscribeService;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.auth.CMRespDto;
import com.cos.photogramstart.web.dto.auth.UserUpdateDto;
import com.cos.photogramstart.web.dto.subscribe.SubscribeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    private final SubscribeService subscribeService;

    @GetMapping("/api/user/{pageUserId}/subscribe")
    public ResponseEntity<?> subscribeList(@PathVariable Long pageUserId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        List<SubscribeDto> subscribeDto = subscribeService.subscribeList(principalDetails.getUser().getId(), pageUserId);
        return new ResponseEntity<>(new CMRespDto<>(1, "구독자 정보 리스트 가져오기 성공", subscribeDto), HttpStatus.OK);
    }

    @PutMapping("/api/user/{id}")
    public CMRespDto<?> update(@PathVariable Long id,
                               @Valid UserUpdateDto userUpdateDto,
                               BindingResult bindingResult,// @Valid 다음 parameter에 적어놔야한다.
                               @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
                System.out.println(error.getDefaultMessage());
            }
            throw new CustomValidationApiException("유효성 검사 실패함", errorMap);
        }else{
            User userEntity = userService.update(id, userUpdateDto.toEntity());
            principalDetails.setUser(userEntity);
            return new CMRespDto<>(1, "회원수정완료", userEntity);
        }
    }
}