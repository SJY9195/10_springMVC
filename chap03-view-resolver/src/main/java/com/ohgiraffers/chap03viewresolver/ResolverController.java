package com.ohgiraffers.chap03viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping

public class ResolverController {

    @GetMapping("string")
    public String stringReturning(Model model){
        model.addAttribute("sendMessage", "문자열로 뷰 이름 반환");
        return "result";
    }

    @GetMapping("string-redirect")
    public String stringRedirect(){
        // 접두사로 redirect: 를 하면 redirect
        return "redirect:/";
    }

    @GetMapping("string-redirect-attr")
    public String stringRedirectFlashAttribute(RedirectAttributes rttr){

        /*
        * 리다이렉트 시 flash 영역에 담아서 redirect 할 수 있다. // flash - 1회용 세션   //flash를 이용하면 attribute는 요청응답후에 끝나는걸 리다이렉트 후에도 attribute가 유지 가능하다.(flashattribute)
        * 자동으로 모델에 추가되기 때문에 requestScope 에서 값을 꺼내면 된다..
        * 세션에 임시로 값을 담고 소멸하는 방식이기 때문에 session 에 동일한 키 값이
        * 존재하지 않아야 된다.
        */
        rttr.addFlashAttribute("flashMessage1", "redirect attr" + " 사용하여 redirect");
        return "redirect:/";

    }

    /*
    * PRG패턴 (Post/ Redirect/ Get)
    * 서버가 post 로 받은 데이터를  처리한 후 리다이렉트 응답을 클라이언트에게 보낸다.
    * 클라이언트는 리다이렉트 된 URL 로 GET 요청을 보내고 그 결과를 화면에 표시한다.
    * 이렇게 되면 이후 새로고침 시에도 GET 요청을 보내기 때문에 중복 데이터 처리가 발생하지
    * 않는다.
    * */

    /*
    * ModelAndView - spring 에서 모델과 뷰를 함께 처리하기 위한 클래스.
    * */

    @GetMapping("modelandview")
    public ModelAndView modelAndView(ModelAndView mv){
        mv.addObject("sendMessage", "modelAndView를 이용한" + " 모델과 뷰 반환");
        mv.setViewName("result");
        return mv;
    }

    @GetMapping("modelandview-redirect")
    public ModelAndView modelAndViewRedirect(ModelAndView mv){
        mv.addObject("flashMesssage2", "test"); //addObject가 명시적으로 url 으로 보여주기 위해 쓰는것이기 때문에 attribute는 안남아있어도 url은 남아있다!
        mv.setViewName("redirect:/");
        return mv;
    }

    @GetMapping("modelandview-redirect-attr")
    public ModelAndView modelAndViewRedirect(ModelAndView mv, RedirectAttributes rttr){
        rttr.addFlashAttribute("flashMessage2", "ModelAndView" + "를 이용한 attr"); //flashAttribute로 넘기는것!!
        mv.setViewName("redirect:/");
        return mv;
    }

}



