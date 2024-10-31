package com.ohgiraffers.chap08securitysession.auth.service;

import com.ohgiraffers.chap08securitysession.auth.model.AuthDetail;
import com.ohgiraffers.chap08securitysession.user.dto.LoginUserDTO;
import com.ohgiraffers.chap08securitysession.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserService userService;

    // 로그인 요청 시 security 가 전달된 사용자의 id를
    // 매개변수로 db에서 사용자의 정보를 찾는다.
    // 구현 해 주어야 함.
    // 전달된 사용자의 객체 타입은 userDetails 를 구현한 구현체가 되어야 한다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUserDTO login = userService.findByUserName(username);

        if(Objects.isNull(login)){
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다..");
        }
        return new AuthDetail(login);
    }

}
