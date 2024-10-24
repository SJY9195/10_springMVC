package com.ohgiraffers.chap05interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //Configuration도 Bean이지만 Bean들을 불러오기 위해 사용하는 클래스이다! // spring의 bean 설정 클래스
public class WebConfiguration implements WebMvcConfigurer {
                                        // spring mvc 설정 추가 용도
    @Autowired
    private StopWatchInterceptor stopWatchInterceptor;     //여기서 전처리, 후처리 메소드가 있는 클래스를  필드에 주입해준것!

    @Override
    public void addInterceptors(InterceptorRegistry registry) { //이게 있어야 디스패처서블릿이 이걸 보고 인터셉터를 한다!
        // 인터셉터 등록
        registry.addInterceptor(stopWatchInterceptor)
                // /stopwatch 경로에 등록한 인터셉터 적용
                .addPathPatterns("/stopwatch")
                .excludePathPatterns("/css/**") //css파일
                .excludePathPatterns("/images/**") // 이미지파일
                .excludePathPatterns("/js/**"); // js 파일
    }
}
