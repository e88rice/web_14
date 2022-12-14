package com.koreait.spring_pj.controller;

import com.koreait.spring_pj.domain.UserEntity;
import com.koreait.spring_pj.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public void login_Get(){
        log.info(" --------------- login_GET -------------------");
    }

    @PostMapping ("/login")
    public String login_Post(){
        log.info(" --------------- login_POST -------------------");
        return "redirect:/board";
    }

    @GetMapping("/register")
    public void register_get(){
        log.info(" --------------- register_GET -------------------");
    }

    @PostMapping("/register")
    public String register_post(UserEntity userEntity){
        log.info(" --------------- register_POST -------------------");
        log.info("abbbbaaaaa :"+userEntity);
        // 회원가입을 시도한다
        userService.user_register(userEntity);
        // 로그인창으로 보낸다
        return "redirect:/users/login";
    }
}
