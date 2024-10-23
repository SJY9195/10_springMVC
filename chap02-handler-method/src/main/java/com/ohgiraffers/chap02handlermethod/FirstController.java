package com.ohgiraffers.chap02handlermethod;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

@Controller
@RequestMapping("/first/*")
@SessionAttributes("id")  // 이 세션은 컨트롤러 내에서 유지된다는 뜻!
public class FirstController {

    @GetMapping("regist")
    public void regist(){} // first 하위에 있는 regist로 가는 경로설정을 보여주기 위한 메소드이다! (메소드 이름은 신경x)

    @PostMapping("regist")
    public String registMenu(Model model, WebRequest request){ //스프링내의 클래스인 WebRequest의 객체인 request에 의해 메소드로 parameter값을 가져올 수 있다.
            String name = request.getParameter("name");
            int price = Integer.parseInt(request.getParameter("price"));
            int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));
            String message = name + "을(를) 신규 메뉴 목록의 " + categoryCode + "번 카테고리에 " + price + "원으로 등록 하셨습니다.";
            System.out.println(message);
            model.addAttribute("message", message);
            return "first/messagePrinter";

        }

    @GetMapping("modify")
    public void modify(){}


    // required = 파라미터 포함 여부, name = 이름, defaultValue = 기본 값 (이거 없더라도 에러가 안날거야 하기 위한) , name을 안붙여주면 오류가 뜰 수 있으므로, 붙여줘야 한다!
//    @PostMapping("modify")
//    public String modifyMenu(Model model,
//                             @RequestParam(required = false, name = "modifyName") String modifyName,
//                             @RequestParam(defaultValue = "0", name = "modifyPrice") int modifyPrice){
//
//        String message = modifyName + " 메뉴 가격을 " + modifyPrice + "원으로 " + "변경 하였습니다. ";
//        System.out.println(message);
//        model.addAttribute("message", message);
//        return "first/messagePrinter";
//        }

    @PostMapping("modify")
    public String modifyMenu(Model model, @RequestParam Map<String,String> parameters) {
        String modifyName = parameters.get("modifyName");
        int modifyPrice = Integer.parseInt(parameters.get("modifyPrice"));

        String message = modifyName + " 메뉴 가격을 " + modifyPrice + "원으로 " + "변경 하였습니다.";
        System.out.println(message);
        model.addAttribute("message", message);
        return "first/messagePrinter";
    }

    @GetMapping("search")
     public void search(){}

    @PostMapping("search")
    public String searchMenu(@ModelAttribute("menu") MenuDTO menu){  //"menu" 는 MenuDTO menu 에 받을 menu와 이름이 똑같아야 한다! (객체이름과)  //만약에 "menu"를 안쓸거면 MenuDTO menuDTO로 이름을 지어줘야한다!
        System.out.println(menu);        //@ModelAttribute는 따로 모델 객체 선언 안해줘도 알아서 Attribute해준다!
        return "first/searchResult";
    }

    // 4. session 이용하기

    @GetMapping("login")
    public void login(){}

    // 4-1 : HttpSession 을 매개변수로 선언하면 핸들러 메소드 호출 시 세션 객체를 호출함.

    @PostMapping("login")
    public String sessionTest1(HttpSession session, @RequestParam(name="id") String id){  //세션 : 사용자마다 서버에서 주어지는 하나의 공간!
        session.setAttribute("id", id);
        return "first/loginResult";
    }

    @GetMapping("logout1") //로그인 세션이 만료되었으면 다시 사용못하게 하는 로직
    public String logoutTest1(HttpSession session){
        session.invalidate();
        return "first/login";
    }

    /*
    * 4-2 SessionAttribute 를 이용하여 session에 값 담기
    * 클래스 레벨에 @SessionAttribute 어노테이션을 이용하여 세션에 값을 담을 key 를
    * 설정해두면, model 영역에 해당 key로 값이 추가되는 경우 Session에 자동 등록한다..
    *   (@SessionAttributes 로 지정된 속성은 해당 컨트롤러 내에서만 유효하다.)
    * */

    @PostMapping("login2")
    public String sessionTest2(Model model, @RequestParam(name="id") String id){
        model.addAttribute("id", id);
        return "first/loginResult";
    }

    //sessionAttribute 로 등록된 값은 session의 상태를 관리하는
    // sessionStatus 의 setComplete() 메소드를 호출해야 사용이 만료된다.  //이 메소드 때문에 로그인1로 로그인해도 로그아웃2로 로그아웃이 가능한 이유이다!
    @GetMapping("logout2")
    public String logoutTest2(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "first/loginResult";
    }

    @GetMapping("body")
    public void body(){}

    /*
    * 5. @RequestBody 를 이용하는 방법
    * 해당 어노테이션은 http 본문 자체를 읽는 부분 (post부분을 body가 담는다는뜻 즉, React에서 뭔가를 받아서 body에 담을때 쓴다!)을
    * 모델로 변환시켜 주는 어노테이션이다.
    * */

    @PostMapping("body")  //json에서 request 받을 때 많이 쓴다!
    public void bodyTest(@RequestBody String body) throws UnsupportedEncodingException {
        System.out.println(body);
        System.out.println(URLDecoder.decode(body, "UTF-8"));
    }

}