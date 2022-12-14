package com.koreait.spring_pj.service;

import com.koreait.spring_pj.dto.ReserveBoardDTO;
import com.koreait.spring_pj.mapper.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {
    @Autowired
    private ManagerMapper managerMapper;

    public List<ReserveBoardDTO> get_all_rooms(){
        return managerMapper.get_all_rooms();
    }

    public boolean insert_room(int roomNo, int roomSize, int roomPrice){
        return managerMapper.insert_room(roomNo, roomSize, roomPrice);
    }
}
