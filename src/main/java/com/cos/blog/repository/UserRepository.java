package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cos.blog.model.User;

// DAO
// 자동으로 bean으로 등록이 된다.
// @Repository : 생략 가능하다.
public interface UserRepository extends JpaRepository<User, Integer> {
// 해당 JpaRepository는 User 테이블을 관리하며, 해당 테이블의 PK는 Integer 형이다.
	Optional<User> findByUsername(String username);
	// SELECT * FROM user WHERE username = ?;
}

//	JPA Naming 쿼리 전략
//User findByUsernameAndPassword(String username, String password);
//		SELECT * FROM user WHERE username = ? AND password = ?;

//@Query(value = "SELECT * FROM user WHERE username = ? AND password = ?", nativeQuery = true)
//User login(String username, String password);
