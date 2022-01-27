package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;

// Bean 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게하는 것
@Configuration	// 빈 등록, IoC 관리
@EnableWebSecurity	// 시큐리티 필터 등록 = 활성화된 시큐리티의 설정을 해당 파일에서 하겠다.
@EnableGlobalMethodSecurity(prePostEnabled = true)	// 특정 주소로 접근하면 권한 및 인증을 미리 체크하겠다는 의미
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PrincipalDetailService principalDetailService;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean	// IoC(함수가 리턴하는 값)
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	// 시큐리티가 대신 로그인 해줄 때 password가 어떤 해쉬가 되어 회원가입되어 있는지 알아야 같은 해쉬로 암호화해서 DB에 있는 해쉬값과 비교할 수 있다.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()	// csrf 토큰 비활성화(테스트시 걸어두는게 좋다.)
			.authorizeRequests()	// request가 들어오면
				.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**")	// /auth로 들어오면 누구나 들어올 수 있다.
				.permitAll()
				.anyRequest()		// 다른 요청들은
				.authenticated()	// 막겠다는 의미
			.and()
				.formLogin()
				.loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/loginProc")	// 스프링 시큐리티가 해당 주소로 요청 오는 로그인을 가로채서 대신 로그인 해준다.
				.defaultSuccessUrl("/");	
	}
	
}
