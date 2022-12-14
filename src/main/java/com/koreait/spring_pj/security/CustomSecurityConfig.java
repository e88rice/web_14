package com.koreait.spring_pj.security;


import com.koreait.spring_pj.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Log4j2
@EnableWebSecurity // WebSecurity 적용하겠다는 의미
// 어노테이션으로 권한을 설정하기 위해서 붙여주는 어노테이션( Security 설정 클래스에 붙여주면 된다 )
// (prePostEnabled => 화면이 뜨기 전에, 요청을 하면 처리하기 전에 시큐리티 활성화 시킴
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor // private final들한테 주입성 주입해주는애래
@Configuration // 설정하는 곳이라고 알려줌
public class CustomSecurityConfig {

    private final DataSource dataSource; // DB연결 해주는 친구 CRUD하게 해줌
    private final CustomUserDetailsService userDetailsService;

    //    BCrypt방식으로 암호화 해주는듯
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); // PasswordEncoder의 자식
    }

    @Bean // 얘를 거쳐서 올 수 있도록 빈으로 등록해줘야 함
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        log.info("--------- security configure ---------");

        // 로그인 페이지 커스텀 세팅
        httpSecurity.formLogin()
                .loginPage("/users/login")
                .successForwardUrl("/board");
//                .failureForwardUrl("/");
//        httpSecurity.logout().logoutUrl("/users/logout")
        // CSRF 토큰 비활성화        
        httpSecurity.csrf().disable(); // CSRF 보안 제거 (귀찮아서)
        // 자동 로그인 설정
        httpSecurity.rememberMe()
                .key("12345678")
                .tokenRepository(persistentTokenRepository())
                .userDetailsService(userDetailsService)
                .tokenValiditySeconds(60*60*24*30); // 자동로그인 해놓으면 30일동안 가능
        //oauth2 로그인 관련 설정 (카카오 로그인 설정)
        httpSecurity.oauth2Login()
                .loginPage("/users/login");

        return httpSecurity.build();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);
        return repository;
    }
    
    // 정적 자원 (static 자원, css, js, image파일 등)을 시큐리티 적용에서 제외시키는 설정
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        log.info(" ------- web security customizer configure ------- ");
        return web -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
    
    
    
}
