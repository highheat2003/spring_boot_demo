package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login", "/create", "/createProcessing").permitAll().antMatchers("/**")
				.authenticated();

		http.formLogin().loginProcessingUrl("/loginProcessing").loginPage("/login").failureUrl("/login?error");

		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/");
	}

	@Configuration
	public static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
		@Autowired
		UserDetailsService userDetailsService;

		@Bean
		PasswordEncoder passwordEncoder() {
			// 스프링에서 제공하는 기본 암호 인코더
			// return new BCryptPasswordEncoder();
			// 커스텀 인코더를 사용하고있다.
			return new MyPasswordEncoder();
		}

		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		}
	}

	// 암호 인코더 커스텀 설정
	public static class MyPasswordEncoder implements PasswordEncoder {
		@Override
		public String encode(CharSequence rawPassword) {
			// 여기서는 이렇게 처리하였지만 예를들어 sha-2 / sha-3 같은 해시를 접목시킬 수 있다.
			// 여기서는 간단히 EN-을 붙여 확인하는 용도!
			return "EN-" + rawPassword.toString();
		}

		@Override
		public boolean matches(CharSequence rawPassword, String encodedPassword) {
			// rawPassword 현재 들어온 값 | encodedPassword 매칭되는 계정에 있는 값
			return encodedPassword.equals(encode(rawPassword));
		}
	}

}
