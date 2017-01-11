package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.domain.Account;
import com.example.domain.LoginUserDetails;

@Service
public class LoginUserDetailsService implements UserDetailsService {

	@Autowired
	AccountService accountService;

	@Override
	public UserDetails loadUserByUsername(String ac) throws UsernameNotFoundException {

		Account account = accountService.getAccount(ac);

		if (account == null) {
			// 계정이 존재하지 않음
			throw new UsernameNotFoundException("login fail");
		}

		return new LoginUserDetails(account);
	}

}
