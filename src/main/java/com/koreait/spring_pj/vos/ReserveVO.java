package com.koreait.spring_pj.vos;


import lombok.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ReserveVO {
    private int reserveNo;
    private int reserveRoom;
    private String reserveUser;
    private LocalDate reserveDate;



}
