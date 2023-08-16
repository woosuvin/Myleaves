package com.itwill.myleaves.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new SimpleUrlAuthenticationFailureHandler("/member/login?error=true");
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http,
												   AuthenticationFailureHandler failureHandler)
												   throws Exception {
		
		// CSRF 기능 비활성화
		http.csrf((crsf) -> crsf.disable());
		
		// 로그인 페이지 커스터마이징
		http.formLogin().loginPage("/member/login") // 사용자 정의 로그인 페이지
        				.loginProcessingUrl("/member/login") // 로그인 처리 URL
        				.usernameParameter("userId") // 아이디 파라미터 이름
        				.passwordParameter("pwd") // 비밀번호 파라미터 이름
        				.defaultSuccessUrl("/")
        				.successHandler(new CustomLoginSuccessHandler())// 로그인 성공 시 이동할 URL
        				.failureHandler(failureHandler); // 로그인 실패 시 이동할 URL

		// 로그아웃 이후 이동할 페이지
		http.logout((logout) -> logout.logoutSuccessUrl("/"));
		
		return http.build();
	}
}