package com.ohgiraffers.chap07fileupload;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //(정적 자원을 쓰기 위해, 특정요청을 바꿔라!!)
public class WebConfig implements WebMvcConfigurer {

    // 정적 자원을 처리하기 위한 메소드

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {  //보안상의 이유로 따로 이미지 경로를 지정해준것이다! 그리고 자바가 직접 경로를 접근 못하기 때문에 지정해준것이다!
        registry.addResourceHandler("/img/single/**")  //컨트롤러에서 실행 시! 디스패처 처리하지말고 여기서 바로 실행시키겠다는 뜻! // 즉, controller 에서 호출 시 → configuration → result.html
                .addResourceLocations("file:///C:/uploads/single/");  //파일 경로 바뀌었을 때 얘만 바꿔주면 되므로 편하다!
        registry.addResourceHandler("/img/multiple/**")
                .addResourceLocations("file:///C:/uploads/multiple/");
    }

}
