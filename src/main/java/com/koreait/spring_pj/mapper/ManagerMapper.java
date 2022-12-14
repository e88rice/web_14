package com.koreait.spring_pj.mapper;

import com.koreait.spring_pj.dto.ReserveBoardDTO;
import com.koreait.spring_pj.vos.RoomVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ManagerMapper {

    List<ReserveBoardDTO> get_all_rooms();

    boolean insert_room(int roomNo, int roomSize, int roomPrice);

}
