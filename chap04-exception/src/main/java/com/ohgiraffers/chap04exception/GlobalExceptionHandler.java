package com.ohgiraffers.chap04exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //Exception 이 발생 했을 때 핸들링 해 주는 클래스를 만드는 어노테이션  //일단 컨트롤러 내부에 exceoptionhandler가 있으면 거기부터 인지를 하고, 없으면 여기 클래스의 controller로 예외처리된다! 즉, 지역 부터!
                    // 포워드는 중복 요청이 되므로 (ex : 같은 게시글이 2번 저장(중복저장))리다이렉트로 중복처리 되기전에 날리므로 CRUD 할때 리다이렉트로 처리한다!!!
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public String nullPinterException(NullPointerException e) {
        System.out.println("Global(서버에서 발생하는 모든)의 Exception 처리");
        return "error/nullPointer";
    }

    @ExceptionHandler(MemberRegistException.class)
    public String userException(Model model, MemberRegistException e) {
        System.out.println("Global 레벨의 exception 처리");
        model.addAttribute("exception", e);
        return "error/memberRegist";
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e){
        System.out.println("나머지 exception 발생함");
        return "error/default";
    }

}
