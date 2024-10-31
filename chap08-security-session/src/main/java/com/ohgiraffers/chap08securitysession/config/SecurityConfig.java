//package com.ohgiraffers.chap08securitysession.config;
//
//import com.ohgiraffers.chap08securitysession.common.UserRole;
//import com.ohgiraffers.chap08securitysession.config.handler.AuthFailHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//@EnableWebSecurity
//
//public class SecurityConfig {  //AuthDetail, AuthService를 쓸거니까 꼭 구현하라고 한다!
//
//    @Autowired
//    private AuthFailHandler authFailHandler;
//
//    // 비밀번호 인코딩 Bean
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    // 정적 리소스 요청 제외 Bean
//    public WebSecurityCustomizer webSecurityCustomizer(){
//        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().
//                atCommonLocations());
//    }
//
//    // 필터체인 커스텀
//    @Bean
//    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//
//        http.authorizeHttpRequests(auth -> { // 서버의 리소스에 접근 가능한 권한 설정
//            //이 요청들은 모든 사용자에게 허용 - 인증 필요 없음.
//            auth.requestMatchers("/auth/login", "/user/signup", "/auth/fail", "/").permitAll();
//            // Role_admin 에게만 권한을 허용하겠다.
//            auth.requestMatchers("/admin/*").hasAnyAuthority(UserRole.ADMIN.getRole());
//            // Role_use 에게만 허용.
//            auth.requestMatchers("/user/*").hasAnyAuthority(UserRole.USER.getRole());
//            auth.anyRequest().authenticated(); //모든 요청을 인증된 사용자에게 허용 해주겠다..
//        }).formLogin(login ->{     //체인 메소드 방식 //연결해서 한줄로 동작하게 한다! (커피주문 커피 -> 컵사이즈 -> 추가메뉴 등 한번에 주문하는것처럼)
//            login.loginPage("/auth/login");
//            login.usernameParameter("user");
//            login.passwordParameter("password"); // html input name 값
//            login.defaultSuccessUrl("/"); // 로그인 성공 시 보낼 곳 설정.. mapping 이 존재해야 함.
//            login.failureHandler(authFailHandler); //실패 시 처리
//        }).logout(logout -> {
//            // 로그아웃 시 요청을 날릴 url 설정 페이지 만들 필요 없음.
//            logout.logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"));
//            // 사용자가 세션을 쓰지 못하게 제거
//            logout.deleteCookies("JSESSIONID"); // 세션이 만들어지면 쿠키가 생기는데, 그걸 지운다!
//            logout.invalidateHttpSession(true); // 세션이 소멸하는걸 허용하는 메소드
//            logout.logoutSuccessUrl("/"); // 로그아웃 완료 후 이동할 페이지 설정
//        }).sessionManagement(session -> {
//            session.maximumSessions(1); // 세션의 갯수 제한 1로 설정 시 중복 로그인 X
//            session.invalidSessionUrl("/"); // 세션 만료시 이동할 페이지
//        }).csrf(csrf->csrf.disable()); // csrf 처리 안함. (공격해온것을 막는것을 안함)(여기서는 공격들어올일이 없으니 할 필요없다.)
//
//        return http.build();
//
//    }
//
//}
package com.ohgiraffers.chap08securitysession.config;

import com.ohgiraffers.chap08securitysession.common.UserRole;
import com.ohgiraffers.chap08securitysession.config.handler.AuthFailHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AuthFailHandler authFailHandler;

    // 비밀번호 인코딩 Bean
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // 정적 리소스 요청 제외 Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.ignoring().requestMatchers
                (PathRequest.toStaticResources().
                        atCommonLocations());
    }

    // 필터체인 커스텀
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests(auth ->{  // 서버의 리소스에 접근 가능한 권한 설정
            //이 요청들은 모든 사용자에게 허용 - 인증 필요 없음.
            auth.requestMatchers("/auth/login", "/user/signup", "/auth/fail", "/").permitAll();
            // Role_admin 에게만 권한을 허용하겠다.
            auth.requestMatchers("/admin/*").hasAnyAuthority(UserRole.ADMIN.getRole());
            // Role_user 에게만 허용
            auth.requestMatchers("/user/*").hasAnyAuthority(UserRole.USER.getRole());
            auth.anyRequest().authenticated(); // 모든 요청을 인증된 사용자에게 허용 해주겠다..
        }).formLogin(login ->{
            login.loginPage("/auth/login");
            login.usernameParameter("user");
            login.passwordParameter("pass"); // html input name 값
            login.defaultSuccessUrl("/"); // 로그인 성공 시 보낼 곳 설정.. mapping 이 존재해야 함.
            login.failureHandler(authFailHandler); // 실패 시 처리
        }).logout(logout -> {
            // 로그아웃 시 요청을 날릴 url 설정 페이지 만들 필요 없음.
            logout.logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"));
            // 사용자가 세션을 쓰지 못하게 제거
            logout.deleteCookies("JSESSIONID");
            logout.invalidateHttpSession(true); // 세션이 소멸하는 걸 허용하는 메소드
            logout.logoutSuccessUrl("/"); // 로그아웃 완료 후 이동할 페이지 설정
        }).sessionManagement(session -> {
            session.maximumSessions(1); // 세션의 갯수 제한 1로 설정 시 중복 로그인 X
            session.invalidSessionUrl("/"); // 세션 만료시 이동할 페이지
        }).csrf(csrf->csrf.disable());  // csrf 처리 안함.

        return http.build();
    }

}
