package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

//@Getter
//@Setter
@Data		//  Getter & Setter
//@AllArgsConstructor	-- 생성자 생성
//@RequiredArgsConstructor		-- final이 붙은 변수들에 대해 생성자를 생성(final이 없는 변수는 X)
@NoArgsConstructor	// Bean 생성자 생성
public class Member {
	private int id;
	private String username;
	private String password;
	private String email;
	// 데이터가 변경되지 않게 하려면 final로 선언(불변성 유지)
	
	@Builder	// 순서 및 변수 개수를 지키지 않아도 생성자에 값을 넣을 수 있게해준다.(실수 및 오류를 줄여준다.) 
	public Member(int id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
}
