package com.koreait.spring_pj.controller;

import com.koreait.spring_pj.dto.UserSecurityDTO;
import com.koreait.spring_pj.vos.RoomVO;
import com.koreait.spring_pj.service.ReserveService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Log4j2
@Controller
public class ReserveController {
    @Autowired
    ReserveService reserveService;

//    @PreAuthorize("isAuthenticated()")
    @GetMapping("/reservation")
    public void reservation(Model model){
        log.info("------- get_reservation -------");
        List<RoomVO> roomVOS = reserveService.get_empty_rooms();
        model.addAttribute("roomVOS", roomVOS);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/reservation/{roomNo}")
    public String reservation(
            @AuthenticationPrincipal UserSecurityDTO userSecurityDTO,
            @PathVariable int roomNo){
        log.info(" --------- reservation POST ----------");
        log.info("ㅎㅇㅎㅇ" + userSecurityDTO);
        reserveService.reserve_room(roomNo, userSecurityDTO.getUserID());
        return "redirect:/board";
    }











}
