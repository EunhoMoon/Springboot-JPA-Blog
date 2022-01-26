package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
/*
 * 서비스가 필요한 이유
 * 
 * 1. 트랜잭션 관리 
 * 2. 서비스 의미 때문
 *  - 하나 이상의 로직이 묶여 하나의 동작을 수행할 수 있도록(모든 로직이 오류가 없어야 동작 - 오류가 나면 롤백)
 */

@Service		// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록해준다.(IoC)
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional	// 전체가 성공해야 commit
	public void joinUser(User user) {
		String rawPassword = user.getPassword();	// 원문
		String encPassword = encoder.encode(rawPassword);	// 해쉬
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
	
//	@Transactional(readOnly = true)	// select할 때 트랜잭션 시작, 서비스 종료시 트랙젝션 종료(정합성 유지 = 여러번 select 하더라도 트랜잭션이 닫혀 있지 않다면 같은 데이터가 select)
//	public User loginUser(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
	
}
