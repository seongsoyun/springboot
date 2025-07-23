package com.smhrd.board.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smhrd.board.entity.UserEntity;
import com.smhrd.board.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    private final MainController mainController;

    UserController(MainController mainController) {
        this.mainController = mainController;
    }
   @Autowired
   UserService userService;
   
   //회원가입 기능 구현
   @PostMapping("/register.do")
   public String register(@RequestParam String id, @RequestParam String pw, @RequestParam String name, @RequestParam int age) {
      // 변수명 == input의 name값과 일치
      
      //필요한 데이터 : 회원가입을 위한 input 태그 데이터
      System.out.println("id :"+id);
      System.out.println("pw :"+pw);
      System.out.println("name :"+name);
      System.out.println("age :"+age);
      //userEntity --> lombok
      UserEntity entity = new UserEntity();
      entity.setUserId(id);
      entity.setPw(pw);
      entity.setName(name);
      entity.setAge(age);
      
      userService.register(entity);
      return "login";
   }
   
   // 로그인 기능 구현
   // 1. 매핑 할 메소드 만들기
   @PostMapping("/login.do")
   public String login(@RequestParam String id, @RequestParam String pw, HttpSession session) {
      
      //2. 필요한 데이터가 무엇인지 생각 (@RequestParam String id, @RequestParam String pw, HttpSession session)
      
      //3. DB접근 
      /* 3.1 service 객체가 생성되어있는지 여부 판단 (@Autowired
   UserService userService;) -> Autowired 확인하기
         3.2 service에 기능 구현하기
        
       
       */
      //3.4 서비스 기능 실행
      
      Optional<UserEntity> entity = userService.login(id, pw);
      //Optional 객체에 .isPresent()
      // --> 데이터가 있으면 true, 없으면 false를 리턴해주는 함수
      
      if(entity.isPresent()) {
         //entity는 현재 Optional 객체
         // UserEntity를 원하기 때문에
         // .get()을 이용하여 optional에 있는 데이터를 가져온다
         session.setAttribute("user", entity.get());
         return "redirect:/";
      }else {
         // 로그인 실패시 alert 창 띄우기
         // queryparameter 전송 가능
         return "redirect:/login?error=true";
      }
      
   }
   
   //로그아웃 기능 만들기
   @GetMapping("/logout")
   public String logout(HttpSession session) {
      // 필요한 데이터
      session.removeAttribute("user");
      return "redirect:/";
   }

}
