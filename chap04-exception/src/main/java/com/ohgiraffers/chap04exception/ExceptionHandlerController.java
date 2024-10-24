package com.ohgiraffers.chap04exception;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionHandlerController {

    @GetMapping("/controller-null")
    public String nullPointExceptionTest(){
        String str = null;
        System.out.println(str.charAt(0));
        return "main";
    }

    // Exception 처리의 우선권을 가진다..      //nullpointerException이 발생했을 시 실행된다!
    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(NullPointerException e){
        System.out.println("controller 레벨의 exception 처리");
        return "error/nullPointer";
    }

    @GetMapping("controller-user")
    public String userException() throws MemberRegistException {  // throw 로 던져주고 catch로 잡아 주는게 예외처리! throw로 예외를 던져주고 cacht 나 exceptionhandler로 잡지 않으면 빨간줄로 쫙 뜬다!!!!
        boolean check = false;
        if(check){
            throw new MemberRegistException("입사가 불가능 합니다.");
        }
        return "main";    // return '/' 일시 html이 없기때문에 main으로 보내지지 않는다. 그러므로 redirect:/ 를 보내주는게 좋다!
    }

    @ExceptionHandler(MemberRegistException.class)
    public String userExceptionHandler(Model model, MemberRegistException e){
        System.out.println("controller 레벨의 exception 처리");
        model.addAttribute("exception", e);
        return "error/memberRegist";
    }

}
