package com.koreait.spring_pj.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Setter
@Getter
@ToString
public class UserSecurityDTO extends User implements OAuth2User {

    private String userID;
    private String userPW;
    private String userName;
    private String userTel;
    private String userEmail;
    private boolean userSocial;
    private Map<String, Object> properties; // 카카오 로그인 시 받아온 정보들

    public UserSecurityDTO(
                            String userID,
                           String userPW,
                           String userName,
                           String userTel,
                           String userEmail,
                           boolean userSocial,
                  Collection<? extends GrantedAuthority> authorities)
    {
        super(userID, userPW, authorities);
        this.userID = userID;
        this.userPW = userPW;
        this.userName = userName;
        this.userTel = userTel;
        this.userEmail = userEmail;
        this.userSocial = userSocial;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.getProperties();
    }

    @Override
    public String getName() {
        return this.userID;
    }

}
