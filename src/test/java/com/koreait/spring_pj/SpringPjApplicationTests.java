package com.koreait.spring_pj;

import com.koreait.spring_pj.domain.UserRole;
import com.koreait.spring_pj.dto.ReserveBoardDTO;
import com.koreait.spring_pj.dto.UserSecurityDTO;
import com.koreait.spring_pj.mapper.ManagerMapper;
import com.koreait.spring_pj.vos.RoomVO;
import com.koreait.spring_pj.mapper.ReserveMapper;
import com.koreait.spring_pj.mapper.UserMapper;
import com.koreait.spring_pj.vos.UserVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Set;

@Log4j2
@SpringBootTest
class SpringPjApplicationTests {
    @Autowired
    ReserveMapper mapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ManagerMapper managerMapper;

    @Test
    void contextLoads() {
        UserVO vo = userMapper.userVO_with_roles("admin");
        log.info(vo);
        log.info("userRoles: "+vo.getUserRoles());
        log.info(vo.getUserID());
        log.info(vo.getUserName());
        log.info(vo.getUserPW());
    }

    @Test
    void ttttesss(){
        log.info(mapper.get_reserved_rooms());
    }

    @Test
    void dfdd() {
        List<ReserveBoardDTO> dtos = managerMapper.get_all_rooms();
        dtos.forEach(x -> {
            log.info("Reserve: " + x.getReserveVO());
            log.info("Room: " + x.getRoomVO());
        });
    }



    @Test
    void test12(){
        log.info(userMapper.userVO_with_roles("e88rice"));
    }




}
