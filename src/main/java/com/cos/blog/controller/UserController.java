package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 인증이 되지 않은 사용자들이 출입할 수 있는 경로 = /auth/**
// 그냥 주소가 / 이면 index.jsp = 허용
// static 이하의 파일들 허용

@Controller
public class UserController {

	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
}
