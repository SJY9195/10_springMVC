package com.ohgiraffers.chap08securitysession.user.dao;

import com.ohgiraffers.chap08securitysession.user.dto.LoginUserDTO;
import com.ohgiraffers.chap08securitysession.user.dto.SignupDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int regist(SignupDTO signupDTO); //bean 만들고 이리로 온다

    LoginUserDTO findByUserName(String username);
}
