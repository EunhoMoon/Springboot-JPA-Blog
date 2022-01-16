package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
	
	// http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome");
		// 파일 리턴 기본 경로 : src/main/resources/static
		// 리턴 명 : /home.html - '/'가 있어야 정상적으로 경로를 찾을 수 있다.
		// 풀 경로 : src/main/resources/static/home.html
		
		return "/home.html";
	}
	
	@GetMapping("/temp/jsp")
	public String tempJsp() {		 
		// prefix : /WEB-INF/views/
		// suffix : .jsp
		
		return "test";
		// JSP는 컴파일을 해야하는 동적 파일(Java)이기 때문에 정적 파일을 찾는 static 위치에서 찾을 수 없다.
	}
	
}
