package com.koreait.spring_pj.service;

import com.koreait.spring_pj.domain.UserRole;
import com.koreait.spring_pj.dto.UserSecurityDTO;
import com.koreait.spring_pj.mapper.UserMapper;
import com.koreait.spring_pj.service.UserService;
import com.koreait.spring_pj.vos.UserVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@Service
// UserDetailsService를 구현하는 객체는 스프링 시큐리티에서 인증을 처리하는 객체가 된다
public class CustomUserDetailsService implements UserDetailsService {

    // 비밀번호를 해싱(암호화) 처리를 담당하는 객체
//    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserMapper userMapper;

    // 실제 인증을 처리할 때 아래 메소드가 자동으로 호출된다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("----- loadUserByUsername_username:  "+ username +" -----");
        log.info("----- " + username + "이 로그인 시도 하였습니다 -----");

        // 1. 해당 username을 가지는 VO객체 DB에서 받아오기
        UserVO userVO = userMapper.userVO_with_roles(username);
        log.info("헤이" + userVO.getUserPW());
        // 2. 해당 VO 객체가 존재하지 않는다면, UsernameNotFoundException을 throw, 예외발생시킴
        if (userVO == null){
            throw new UsernameNotFoundException(username +": 유저는 존재하지 않음");
        }
        // 3. 존재한다면 DTO를 생성해서 반환시키기
        // VO 내용을 그대로 (getter를 사용하여) DTO 객체를 만들고 반환시키면 됨
        UserSecurityDTO userSecurityDTO = new UserSecurityDTO
                (
                username, userVO.getUserPW(), userVO.getUserName(),
                userVO.getUserTel(), userVO.getUserEmail(), userVO.isUserSocial(),
                // stream = 요소를 순서대로 확인함
                // map = 포이치나 이터레이터같은걸로 순회할 때,
                //      그 안의 요소를 다른 형태로 변경할 수 있음(진짜 바뀌는건 아니고 순회 도는중에만)
                // DTO에 설정되어있는 컬렉션에 맞는 형태로 변환(GrantedAuthority)한 후에
                // .collect.toList(리스트인 컬렉션 형태로 변경해줌) 완성^^
                userVO.getUserRoles().stream()
                        .map(x -> new SimpleGrantedAuthority("ROLE_" + x.name()))
                        .collect(Collectors.toList())
        );
        return userSecurityDTO;
    }
}
