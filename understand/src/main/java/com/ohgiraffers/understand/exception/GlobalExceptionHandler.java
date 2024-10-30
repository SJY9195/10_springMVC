package com.ohgiraffers.understand.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public String nullPointExceptionhandler(NullPointerException e, Model model){
        System.out.println("global 레벨의 NullPointerException 처리");
        model.addAttribute("message", "빈 값이 존재합니다.");
        return "error/errorMessage";
    }

    // DB 제약조건 위반 시 발생 에러
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public String SQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e, Model model){
        model.addAttribute("message", "DB 제약 조건 위반 발생!");
        return "error/errorMessage";
    }

    @ExceptionHandler(SQLSyntaxErrorException.class)
    public String SQLSyntaxErrorExceptionHandler(SQLSyntaxErrorException e, Model model){
        model.addAttribute("message", "쿼리 작성문이 잘못 되었습니다.");
        return "error/errorMessage";
    }

    // 우리가 만든 에러
    @ExceptionHandler(NotInsertNameException.class)
    public String notInsertNameExceptionHandler(NotInsertNameException e, Model model){
        model.addAttribute("message", "이미 있는 메뉴 이거나 공란 입니다.");
        return "error/errorMessage";
    }

    // 요청한 페이지를 찾을 수 없을 시
    @ExceptionHandler(NoResourceFoundException.class)
    public String NoResourceFoundExceptionHandler(NoResourceFoundException e, Model model){
        model.addAttribute("message", "요청하신 페이지를 찾을 수 없습니다.");
        return "error/errorMessage";
    }

    // 그 외 나머지
    @ExceptionHandler(Exception.class)
    public String otherExceptionHandler(Exception e, Model model){
        model.addAttribute("message", "오류가 발생했습니다.");
        return "error/errorMessage";
    }

//    package com.ohgiraffers.understand.exception; 강사님 코드
//
//  import org.springframework.dao.DataAccessException;
//  import org.springframework.dao.DataIntegrityViolationException;
//  import org.springframework.ui.Model;
//  import org.springframework.web.bind.annotation.ControllerAdvice;
//  import org.springframework.web.bind.annotation.ExceptionHandler;
//  import org.springframework.web.servlet.resource.NoResourceFoundException;
//
//  import java.sql.SQLIntegrityConstraintViolationException;
//  import java.sql.SQLSyntaxErrorException;
//
//    @ControllerAdvice
//    public class GlobalExceptionHandler {
//
//        @ExceptionHandler(NullPointerException.class)
//        public String nullPointExceptionHandler(NullPointerException e, Model model){
//            System.out.println("global 레벨의 NullPointerException 처리");
//            model.addAttribute("message", "빈 값이 존재합니다.");
//            return "error/errorMessage";
//        }
//
//        // DB 제약조건 위반 시 발생 에러
//        @ExceptionHandler(DataIntegrityViolationException.class)
//        public String SQLIntegrityConstraintViolationException(DataIntegrityViolationException  e,Model model){
//            model.addAttribute("message","DB 제약 조건 위반 발생!");
//            return "error/errorMessage";
//        }
//
//        @ExceptionHandler(DataAccessException.class)
//        public String SQLSyntaxErrorExceptionHandler(DataAccessException  e, Model model){
//            model.addAttribute("message", "쿼리 작성문이 잘못 되었습니다.");
//            return "error/errorMessage";
//        }
//
//        // 우리가 만든 에러
//        @ExceptionHandler(NotInsertNameException.class)
//        public String notInsertNameExceptionHandler(NotInsertNameException e, Model model){
//            model.addAttribute("message","이미 있는 메뉴 이거나 공란 입니다.");
//            return "error/errorMessage";
//        }
//
//        // 요청한 페이지를 찾을 수 없을 시
//        @ExceptionHandler(NoResourceFoundException.class)
//        public String NoResourceFoundExceptionHandler(NoResourceFoundException e,Model model){
//            model.addAttribute("message","요청하신 페이지를 찾을 수 없습니다.");
//            return "error/errorMessage";
//        }
//
//        // 그 외 나머지
//        @ExceptionHandler(Exception.class)
//        public String otherExceptionHandler(Exception e,Model model){
//            model.addAttribute("message","오류가 발생했습니다.");
//            return "error/errorMessage";
//        }
//
//    }

}
