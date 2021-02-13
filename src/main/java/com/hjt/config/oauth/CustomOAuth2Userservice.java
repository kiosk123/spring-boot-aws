package com.hjt.config.oauth;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import javax.persistence.Entity;
import javax.servlet.http.HttpSession;

import com.hjt.config.oauth.dto.OAuthAttributes;
import com.hjt.config.oauth.dto.SessionUser;
import com.hjt.domain.user.User;
import com.hjt.domain.user.UserRepository;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomOAuth2Userservice implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 현재 로그인 진행중인 서비스 구분
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        // log.info("registrationId : " + registrationId);

        // OAuth 로그인 진행시 키가 되는 필드값
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint()
                .getUserNameAttributeName();
        // log.info("userNameAttributeName : " + userNameAttributeName);

        //OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스 - 네이버등 다른 소셜 로그인도 이 클래스를 사용한다.
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName,
                oAuth2User.getAttributes());
        
        // oAuth2User.getAttributes().entrySet().stream().forEach(e -> {
        //     log.info("oAuth2User " + e.getKey() + " : " + e.getValue());
        // });
        
        User user = saveOrUpdate(attributes);

        // SessionUser 세션에 사용자 정보 저장을 위한클래스
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(), attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository
                .findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}
