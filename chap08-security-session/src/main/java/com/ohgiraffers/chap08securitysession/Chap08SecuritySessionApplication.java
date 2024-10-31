package com.ohgiraffers.chap08securitysession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//세션에서 세션 아이디 발급 받는 시큐리티는 MSA인 서버 내에서 세션이 보안 되기 때문에 안전하나, 큰 규모에서는 적합하지 않다.
@SpringBootApplication
public class Chap08SecuritySessionApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chap08SecuritySessionApplication.class, args);
    }

}
