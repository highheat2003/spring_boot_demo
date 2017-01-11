package com.example.domain;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class LoginUserDetails extends User {
	
	private String ac;
	
	public String getAc() {
		return ac;
	}

	public LoginUserDetails(Account account) {		
		super(account.getAc(), account.getPw(), AuthorityUtils.createAuthorityList(account.getRole()));
		ac = account.getAc();
	}
	
}
