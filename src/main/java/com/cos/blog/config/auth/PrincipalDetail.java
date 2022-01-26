package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고, 
// UserDetails 타입의 오브젝트를 스프링 시큐리티의 고유의 세션저장소에 저장해준다.
public class PrincipalDetail implements UserDetails {
// 시큐리티 설정을 커스터마이징하기 위한 클래스

	private User user; // 컴포지션 : 객체를 품고 있는 것

	public PrincipalDetail(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 계정이 만료되지 않았는지 리턴한다.(true : 만료 안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정이 잠겨있지 않은지 리턴(true : 잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비밀번호의 만료 여부를 리턴(true : 만료되지 않음)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정의 활성화 여부 리턴(true : 활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}

	// 계정의 권한 목록을 리턴(권한이 여러개 있을 경우 루프를 돌아야 한다.)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> collectors = new ArrayList<>();
//		collectors.add(new GrantedAuthority() {
//			
//			@Override
//			public String getAuthority() {
//				return "ROLE_" + user.getRole();	// ROLE_USER 의 형태로 return 되어야 확인이 가능하다.(ROLE_을 prefix로 붙여주어야 한다.)
//			}
//		});
		collectors.add(() -> {
			return "ROLE_" + user.getRole();
		});

		return collectors;
	}

}
