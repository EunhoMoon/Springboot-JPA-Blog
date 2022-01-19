package com.cos.blog.test;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController	// html이 아닌 data를 리턴해주는 controller
public class DummyControllerTest {
	
	@Autowired	// DummyControllerTest가 메모리에 뜰 때 @Autowired로 연결된 객체도 같이 뜬다.(의존성 주입)
	private UserRepository userRepository;
	
	// {id} 주소로 파라미터를 전달받을 수 있다.
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 존재하지 않습니다. id : " + id);
			}
			
		});
		// 찾는 값이 DB에 없을 경우 null값이 리턴되기 때문에 Optional로 User객체를 감싸서 가져온다.
	
		// 람다식
//		User user = userRepository.findById(id).orElseThrow(() -> {
//			return new IllegalArgumentException("해당 유저는 존재하지 않습니다. id : " + id);
//		});
		
		// 요청 = 웹 브라우저 / user 객체 = Java Object 
		// 웹 브라우저가 이해할 수 있는 데이터로 변환(json)
		// 스프링 부트 = MessageConverter가 응답시에 자동으로 작동(자바 오브젝트를 리턴하게 될 경우 Jackson 라이브러리를 호출해서 json으로 변환 후 브라우저에 응담)
		return user;
	}

	// http의 body에 있는 username, password, email 데이터를 가지고 요청
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("id : " + user.getId());
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("role : " + user.getRole());
		System.out.println("createDate : " + user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
	
}
