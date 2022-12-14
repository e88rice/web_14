package com.koreait.spring_pj.vos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RoomVO {
    private int roomNo;
    private int roomSize;
    private int roomPrice;
}
