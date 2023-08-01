package com.itwill.myleaves.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		// CSRF 기능 비활성화
		http.csrf((crsf) -> crsf.disable());
		
		// 기본 로그인 페이지
		http.formLogin(Customizer.withDefaults());
		
		// 로그아웃 이후 이동할 페이지
		http.logout((logout) -> logout.logoutSuccessUrl("/"));
		
		return http.build();
	}

}