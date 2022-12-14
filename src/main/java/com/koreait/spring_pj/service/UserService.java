package com.koreait.spring_pj.service;

import com.koreait.spring_pj.domain.UserEntity;
import com.koreait.spring_pj.mapper.UserMapper;
import com.koreait.spring_pj.vos.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public boolean user_register(UserEntity userEntity){
        // 정직하게 회원가입 버튼을 통해 회원가입한 회원
        userEntity.setUserPW(passwordEncoder.encode(userEntity.getUserPW())); // 컨트롤러에 보내주기전에 해당 유저의 비밀번호를 암호화 인코딩해서 전송한다
        userEntity.setUserSocial(false); // 기본적으로 소셜은 false
        // 신규 유저를 회원가입시키고 일반권한 부여
        return mapper.user_register(userEntity) && mapper.user_register_authorize(userEntity.getUserID());
    }
}
