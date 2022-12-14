package com.koreait.spring_pj.controller;

import com.koreait.spring_pj.vos.ReserveVO;
import com.koreait.spring_pj.vos.UserVO;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Log4j2
@Controller
public class HomeController {
    @GetMapping("/")
    public String init(){
        log.info("Home Controller 실행");
        return "redirect:/board";
    }

}


