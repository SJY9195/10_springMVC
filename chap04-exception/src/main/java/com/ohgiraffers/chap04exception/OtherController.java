package com.ohgiraffers.chap04exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OtherController {   // 예외처리를 위한 오류를 내는 로직이 있는 클래스

    @GetMapping("other-controller-null")
    public String otherNullPointerException(){
        String str = null;
        System.out.println(str.charAt(0));
        return "main";
    }

    @GetMapping("other-controller-user")
    public String otherUerExceptionTest() throws MemberRegistException {
        if(true){
            throw new MemberRegistException("입사 불가");
        }
        return "main";
    }

    @GetMapping("other-controller-array")
    public String otherArrayExceptionTest(){
        int[] array = new int[0];
        System.out.println(array[0]);
        return "main";
    }

}
