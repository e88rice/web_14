package com.koreait.spring_pj.service;

import com.koreait.spring_pj.dto.ReserveBoardDTO;
import com.koreait.spring_pj.vos.RoomVO;
import com.koreait.spring_pj.mapper.ReserveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReserveService {
    @Autowired
    private ReserveMapper mapper;

    public List<RoomVO> get_empty_rooms(){
        return mapper.get_empty_rooms();
    }

    public List<ReserveBoardDTO> get_reserved_rooms(){
        return mapper.get_reserved_rooms();
    }

    public boolean reserve_room(int roomNo, String userID){
        return mapper.reserve_room(roomNo, userID);
    }

}
