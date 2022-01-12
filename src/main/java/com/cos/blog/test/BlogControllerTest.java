package com.cos.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController	
// 스프링이 현재 위치한 패키지의 하위 패키지들을 스캔한 후, 
// 특정 Annotation이 붙어있는 클래스 파일들을 찾아 스프링 컨테이너에서 관리
public class BlogControllerTest {
	
	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>Hello Spring boot</h1>";
	}
}
