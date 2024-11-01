package com.ohgiraffers.chap09websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChatController {

    @GetMapping("/chat")
    public ModelAndView chatPage(@RequestParam("userId") String userId, ModelAndView mv){
        System.out.println("userId = " + userId);
        mv.addObject("userId", userId);
        mv.setViewName("chatWindow");
        return mv;
    }
}
