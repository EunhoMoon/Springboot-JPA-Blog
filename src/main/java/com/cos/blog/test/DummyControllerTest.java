package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController	// html이 아닌 data를 리턴해주는 controller
public class DummyControllerTest {
	
	@Autowired	// DummyControllerTest가 메모리에 뜰 때 @Autowired로 연결된 객체도 같이 뜬다.(의존성 주입)
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String deleteUser(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {	// 그냥 Exception으로 해도 된다.(최고 부모 객체이기 때문)
			return "DB에 해당 ID가 존재하지 않아 삭제에 실패하였습니다.";
		}
		// 삭제할 데이터가 없을 경우가 있기 때문에 try-catch문 안에서 실행한다.
		
		return "User ID : " + id + "가 삭제 되었습니다.";
	}
	
	// save 함수는 id를 전달하지 않으면 insert를 해주고, id를 전달했을 때 해당 id가 있으면 update, 없으면 insert를 해준다.
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public String updateUser(@PathVariable int id, @RequestBody User requestUser) {
	// @RequestBody : json 데이터를 요청하면 Java Object로 변환(MessageConverter의 Jackson 라이브러리)해서 받아준다.
		System.out.println("id : " + id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		// null 값이 들어가지 않도록 id를 이용해 데이터를 담아와서 변경한다.(null 값이 없는 꽉 찬 데이터)
		
		//	userRepository.save(user);		// 업데이트 할 때는 save를 잘 사용하지 않는다.(null 값이 들어갈 수 있기 때문)
		// 더티 체킹 :  찌꺼기 데이터를 체크해서 날려버리는 것 -> 변경을 감지하는 것(Transaction 등)
		// return user;
		return "업데이트에 성공했습니다.";
	}
	
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	// 한 페이지당 두 건의 데이터를 리턴받는다.
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		// getContent() :  Json 데이터만 List 형태로 리턴
		return users;
	}
	
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
