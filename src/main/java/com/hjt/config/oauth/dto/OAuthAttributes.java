package com.hjt.config.oauth.dto;

import java.util.Map;

import com.hjt.domain.user.Role;
import com.hjt.domain.user.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {

	private Map<String, Object> attributes;
	private String nameAttributeKey;
	private String name;
	private String email;
	private String picture;

	@Builder
	public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email,
			String picture) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.name = name;
		this.email = email;
		this.picture = picture;
	}

	// OAuth2User에서 사용자 정보는 Map이기 때문에 값 하나하나를 변환해야한다.
	public static OAuthAttributes of(String registrationId, String userNameAttributeName,
			Map<String, Object> attributes) {
		return ofGoogle(userNameAttributeName, attributes);
	}

	private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
		return OAuthAttributes.builder().name((String)attributes.get("name"))
										.email((String)attributes.get("email"))
										.picture((String)attributes.get("picture"))
										.attributes(attributes)
										.nameAttributeKey(userNameAttributeName)
										.build();
	}

	/**
	 * User 엔티티를 생성한다
	 * OAuthAttributes에서 엔티티를 생성하는 시점은 처음 가입할 때이다.
	 * 가입할 때의 기본 권한을 GUEST로 주기 위해서 role 빌더값에는 Role.GUEST를 사용한다.
	 * OAuthAttributes 클래스 생성이 끝났으면 같은 패키지에 SessionUser 클래스를 생성한다.
	 * @return
	 */
	public User toEntity() {
		return User.builder().name(name).email(email).picture(picture).role(Role.GUEST).build();
	}

}
