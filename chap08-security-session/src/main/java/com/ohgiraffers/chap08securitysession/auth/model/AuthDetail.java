package com.ohgiraffers.chap08securitysession.auth.model;

import com.ohgiraffers.chap08securitysession.user.dto.LoginUserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AuthDetail implements UserDetails {

    private LoginUserDTO loginUserDTO;

    public AuthDetail(){
    }

    public AuthDetail(LoginUserDTO loginUserDTO) {
        this.loginUserDTO = loginUserDTO;
    }

    // 계정 만료 여부를 표현하는 메소드 - false 이면 해당 계정을 사용할 수 없다.
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    } //계정만료면 샤용할 수 없게하는 메소드!

    // 잠겨있는 계정을 확인하는 메소드 - false 면 사용할 수 없다.
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 탈퇴 계정 여부 표현 메소드 - false 면 사용할 수 없다.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 비활성화로 사용자가 사용 못하는 경우
    @Override
    public boolean isEnabled() {
        return true;
    }

    // 권한 정보 반환 메소드 확인
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {   //시큐리티에서 원하는 자료형을 맞추기위한 메소드
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : loginUserDTO.getRole()){
            authorities.add(new GrantedAuthority(){ //바로 구현체(상속받은것)를 넣어줌
                @Override
                public String getAuthority() {
                    return role;
                }
            });
        }
        return authorities;
    }

    // 사용자의 비밀번호를 반환하는 메소드
    @Override
    public String getPassword() {
        return loginUserDTO.getPassword();
    }

    // 사용자의 아이디를 반환하는 메소드
    @Override
    public String getUsername() {
        return loginUserDTO.getUserId();
    }    // 사용자가 입력한 값이랑 DB에 있는값이랑 비교하기 위해 구현한 메소드!!


}
