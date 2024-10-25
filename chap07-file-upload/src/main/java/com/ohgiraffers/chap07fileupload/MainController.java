package com.ohgiraffers.chap07fileupload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping(value = {"/", "/main"})
    public String defaultLocation(){
        return "main";
    }

}
