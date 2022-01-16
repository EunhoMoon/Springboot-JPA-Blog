package com.cos.blog.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder	// 빌더 패턴
//ORM -> Java(다른 언어) Object -> 테이블로 매핑해주는 기술
@Entity	// User 클래스가 MySQL에 테이블로 생성이 된다.
public class User {
	
	@Id	// Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.(DB 프로그램에 따른다는 의미)
	private int id;		//	시퀀스, auto_increment
	
	@Column(nullable = false, length = 30)	// not null, 글자 수 제한
	private String username;		// 아이디
	
	@Column(nullable = false, length = 100)	
	// 암호화(Hash)를 위해 넉넉하게 글자 수 지정
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	@ColumnDefault("'user'")	// ''로 문자열이라는 것을 알려줘야 한다.
	private String role;	
	// admin, user, manager 회원 권한 지정
	// Enum을 쓰는게 좋다.(데이터의 Domain을 만들어 줄 수 있기 때문 - String은 잘못된 값이 입력될 수 있다.)
	
	@CreationTimestamp	// table에 insert될 때 시간 자동으로 입력(date now())
	private Timestamp createDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
}
