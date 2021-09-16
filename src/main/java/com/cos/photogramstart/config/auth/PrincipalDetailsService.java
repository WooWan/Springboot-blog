package com.cos.photogramstart.config.auth;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//userdetail service는 login요청이 들어오면 해당 요청을 낚아챈다
//return이 정상적으로 되면 session이 정상적으로 만들어진다.
@RequiredArgsConstructor
@Service //ioc
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    //login요청이 들어오면 UserDetailsService가 호출되는데, principalDetailsService가
    //ioc로 등록되면 같은 타입인 userDetailservice대신 principal 이 실행된다
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            return null;
        }else{
            return new PrincipalDetails(userEntity);
        }
    }
}
