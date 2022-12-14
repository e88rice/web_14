package com.koreait.spring_pj.dto;

import com.koreait.spring_pj.vos.ReserveVO;
import com.koreait.spring_pj.vos.RoomVO;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReserveBoardDTO {
    ReserveVO reserveVO;
    RoomVO roomVO;

}
