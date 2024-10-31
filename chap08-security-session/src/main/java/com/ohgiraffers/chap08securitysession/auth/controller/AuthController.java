package com.ohgiraffers.chap08securitysession.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth/*")
public class AuthController {

    @GetMapping("login")
    public ModelAndView login(ModelAndView mv) {
        mv.setViewName("auth/login");
        return mv;
    }

    @GetMapping("fail")
    public ModelAndView loginFail(@RequestParam String message, ModelAndView mv) {  //exception 처리하고 로그인 실패 했을시 여기로 보내줌
        mv.addObject("message", message);
        mv.setViewName("auth/fail");
        return mv;
    }

}
