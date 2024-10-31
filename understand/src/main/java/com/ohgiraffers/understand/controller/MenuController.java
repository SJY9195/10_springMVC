package com.ohgiraffers.understand.controller;

import com.ohgiraffers.understand.dto.MenuDTO;
import com.ohgiraffers.understand.exception.NotInsertNameException;
import com.ohgiraffers.understand.model.MenuDAO;
import com.ohgiraffers.understand.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/menus/*")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuDAO menuDAO;

    @GetMapping("menus")
    public ModelAndView selectAllMenu(ModelAndView mv){
        List<MenuDTO> menus = menuService.selectAllMenu();
        if(Objects.isNull(menus)){
            throw new NullPointerException();
        }  // 검증 로직
        mv.addObject("menus", menus);
        mv.setViewName("menus/allMenus");
        return mv;
    }

    @GetMapping("onemenu")
    public ModelAndView oneMenu(ModelAndView mv){
        mv.setViewName("menus/oneMenu");
        return mv;
    }

    @GetMapping("onemenuaction")
    public ModelAndView selectOneMenu(ModelAndView mv, MenuDTO menuDTO){  //스프링에서는 html에 있는 code(MenuDTO에 있는 필드)를 이렇게 매개변수를 써서 받아 올 수있다.
        // 로직
        MenuDTO menu = menuService.selectOneMenu(menuDTO);
        mv.addObject("menus", menu);
        mv.setViewName("menus/allMenus");
        return mv;
    }

    @GetMapping("regist")
    public ModelAndView insert(ModelAndView mv){
        mv.setViewName("menus/regist");
        return mv;
    }

    @PostMapping("regist")
    public ModelAndView insertMenu(ModelAndView mv, MenuDTO menuDTO) throws NotInsertNameException {
        int regist = menuService.regist(menuDTO);

        if(regist<=0){
            mv.addObject("message", "가격은 음수일 수 없습니다.");
            mv.setViewName("/error/errorMessage");
        }else{
            mv.setViewName("menus/returnMessage");
        }

        return mv;
    }

    @GetMapping("update")
    public ModelAndView update(ModelAndView mv){
        mv.setViewName("menus/update");
        return mv;
    }

    @PostMapping("update")
//    public ModelAndView updateMenu(ModelAndView mv, MenuDTO menuDTO){
            public ModelAndView updateMenu(ModelAndView mv, @RequestParam int code, @RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "0") int price,
                                            @RequestParam(defaultValue = "0") int categoryCode){
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setCode(code);
        menuDTO.setName(name);
        menuDTO.setPrice(price);
        menuDTO.setCategoryCode(categoryCode);
        int update = menuService.update(menuDTO);
        if(update<=0){
            mv.addObject("message", "업데이트 실패");
            mv.setViewName("/error/errorMessage");
        }else{
            mv.setViewName("/menus/returnMessage");
        }
        return mv;
    }

    @GetMapping("delete")
    public ModelAndView delete(ModelAndView mv){
        mv.setViewName("menus/delete");
        return mv;
    }

    @PostMapping("delete")
    public ModelAndView deleteMenu(ModelAndView mv, MenuDTO menuDTO){
        int delete = menuService.delete(menuDTO);
        if(delete <= 0){
            mv.addObject("message", "삭제 실패");
            mv.setViewName("/error/errorMessage");
        }else{
            mv.setViewName("/menus/returnMessage");
        }
        return mv;
    }

}
