package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Controller : 사용자의 요청에 대해  HTML파일로 응답
// RestController : 사용자의 요청에 대해 Data로 응답

@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest : ";
	
	@GetMapping("http/lombok")
	public String lombokTest() {
		Member m = Member.builder().username("ssar").password("1234").email("ssar@nate.com").build();
		System.out.println(TAG + " getter : " + m.getUsername());
		m.setUsername("cos");
		System.out.println(TAG + " setter : " + m.getUsername());
		
		return "lombok test 완료";
	}
	
	// get(select) : Internet browser 요청은 get밖에 할 수 없다.
	@GetMapping("/http/get")
	public String getTest(Member m) {
		return "get 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
	} 
	
	// post(insert) 
	@PostMapping("/http/post")
	public String postTest() {
		return "post 요청";
	}
	
	// put(update)
	@PutMapping("/http/put")
	public String putTest() {
		return "put 요청";
	}
	
	// delete(delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
