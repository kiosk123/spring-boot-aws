package com.hjt.config.oauth;

import com.hjt.domain.user.Role;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2Userservice customOAuth2Userservice;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .headers().frameOptions().disable() // for h2-console spring config
            .and()
            .authorizeRequests() // authorizeRequests : 각 url 별로 권한 관리를 설정하는 옵션의 시작점 
            .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
            .antMatchers("/api/v1/**").hasRole(Role.USER.name())
            .anyRequest() // 설정된 값들 이외 나머지 URL을 나타냄
            .authenticated() // 나머지 URL은 모두 인증된 사용자만 허용하게 함
            .and().logout().logoutSuccessUrl("/") // 로그아웃 성공시 / 주소로 이동
            .and()
            .oauth2Login() // OAuth2 로그인 기능에 대한 설정의 진입점
            .userInfoEndpoint() // OAuth2 로그인 성공 이후 사용자 정보를 가져올때 설정을 담당
            .userService(customOAuth2Userservice); // 소셜 로그인 성공후 후속 조치를 진행할 UserService 인터페이스의 구현제를 등록
                                                   // 리소스서버(소셜서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시
    }
    
}
