package com.koreait.spring_pj.domain;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
// DB와 완전 동일하게 세팅
public class UserEntity {

    private String userID;
    private String userPW;
    private String userName;
    private String userTel;
    private String userEmail;
    private boolean userSocial;

}
