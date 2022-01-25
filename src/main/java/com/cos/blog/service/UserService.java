package com.cos.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	@Transactional	// 전체가 성공해야 commit
	public int joinUser(User user) {
		try {
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("UserService : joinUser : " + e.getMessage());
		}
		return -1;
	}
	
}
