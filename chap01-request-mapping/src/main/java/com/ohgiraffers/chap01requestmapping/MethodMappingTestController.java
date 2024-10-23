package com.ohgiraffers.chap01requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller //스프링부트가 요청을 받으면 어노테이션 되어있는 Controller를 찾아서 뒤진다!
public class MethodMappingTestController {

    @RequestMapping("/menu/regist")
    public String registMenu(Model model){
        model.addAttribute("message", "신규 메뉴 등록용 핸들러 메소드 호출");

        return "mappingResult"; //스프링부트자체내의 로직에의해 이 html을 찾아서 리턴한다.
    }

    @RequestMapping(value = "/menu/modify", method = RequestMethod.GET)
    public String modifyMenu(Model model){
        model.addAttribute("message", "Get 방식의 메뉴 수정 호출");
        return "mappingResult";
    }

    /*
    * 요청 메소드 전용 어노테이션
    *   요청 메소드         어노테이션
    *       post            @PostMapping
    *       get             @GetMapping
    *       Put             @PutMapping // 수정 리소스의 모든 것을 업데이트
    *       Delete          @DeleteMapping //데이터를 직접 삭제하는건 리스크가 크므로 거의 안한다!
    *       Patch           @PatchMapping // 수정 리소스 일부 업데이트
    * */

    @GetMapping("/menu/delete")
    public String deleteMenu(Model model){
        model.addAttribute("message", "Get 방식의 삭제 핸들러 메소드 호출");
        return "mappingResult";
    }

    @PostMapping("/menu/delete")
    public String postDeleteMenu(Model model){
        model.addAttribute("message", "Post 방식의 삭제 핸들러 메소드 호출");
        return "mappingResult";
    }
}
