package com.koreait.spring_pj.controller;

import com.koreait.spring_pj.service.ReserveService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Log4j2
@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private ReserveService reserveService;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public void board(Model model){
       model.addAttribute("reserveBoardDTO", reserveService.get_reserved_rooms());
    }

}
