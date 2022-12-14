package com.koreait.spring_pj.vos;

import com.koreait.spring_pj.domain.UserRole;
import com.koreait.spring_pj.domain.UserEntity;
import lombok.*;
import java.util.Set;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserVO extends UserEntity {
    // 권한은 중복이 안되니까 세트로 만듬
    private Set<UserRole> userRoles;


}
