package com.ohgiraffers.chap01requestmapping;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/order/*")     //http://localhost:9090/order/* 오더로 시작하는 요청이 들어오면 이클래스에서 시작하겠다!!를 지정
public class ClassMappingTestController {    // 패키지관리가 수월해지기 때문에 쓰는거지 큰 성능이 좋아지는 건 아니다!

    @GetMapping("/regist")
    public String registOrder(Model model){
        model.addAttribute("message", "Get 방식의" + "주문 등록용 핸들러 메소드 호출");
        return "mappingResult";   //html return은 Controller만 해준다! (다른것들은 viewresolver가 동작x)
    }

    @RequestMapping(value = {"modify", "delete"}, method = RequestMethod.POST)   //RequestMapping은 여러개의 경로를 설정할 수 있다!! (하나의 메소드에)
    public String modifyOrder(Model model){
        model.addAttribute("message", "post 방식의" + "주문 정보 수정 핸들러 메소드 호출");
        return "mappingResult";
    }

    // /order/1  pathvariable
    // /order?asd=asd queryString parameter

    /*
    * PathVariable
    * @Pathvariable 어노테이션을 이용해 요청을 변수를 받아올 수 있다..
    * path variable 로 전달되는 {변수명} 은 반드시 매개변수명과 동일해야 한다.
    * 만약 동일하지 않으면 @PathVariable("이름") 을 설정 해 주어야 한다.
    * */
    @GetMapping("/detail/{orderNo}")   // 클라이언트에게 요청받았을 시 html에서 orderNo 3을 받아온것이다!
    public String selectOrderDetail(Model model, @PathVariable("orderNo") int orderNo){
        model.addAttribute("message", orderNo+"번 주문 " + "상세 내용 조회용 핸들러 메소드 호출");
        return "mappingResult";
    }

    @RequestMapping
    public String otherReqeust(Model model){  // order/경로가 설정 외의 것이 들어왔을 때 이 메소드 작동!!
        model.addAttribute("message", "order 요청이긴 하지만" + "다른 기능이 준비되지 않음");
        return "mappingResult";
    }

}
