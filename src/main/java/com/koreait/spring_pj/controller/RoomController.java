package com.koreait.spring_pj.controller;


import com.koreait.spring_pj.dto.ReserveBoardDTO;
import com.koreait.spring_pj.service.ManagerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private ManagerService managerService;


    @GetMapping
    public List<ReserveBoardDTO> get_rooms(){
        return managerService.get_all_rooms();
    }

    @PostMapping
    public void insert_room(int roomNo, int roomSize, int roomPrice, HttpServletResponse response) throws IOException {
        managerService.insert_room(roomNo, roomSize, roomPrice);
        response.sendRedirect("http://localhost:8080/manager/room");
        }

        @DeleteMapping
    public void delete_room(){
        log.info(" --------------- 방 삭제 ----------------");
        }


    }


