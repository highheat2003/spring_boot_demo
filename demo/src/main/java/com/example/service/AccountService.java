package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Account;
import com.example.mapper.AccountMapper;

@Component
public class AccountService {

	@Autowired
	private AccountMapper accountMapper;

	public Account getAccount(String ac) {
		return accountMapper.findOneByAc(ac);
	}

	@Transactional
	public boolean create(String ac, String pw) {
		if (accountMapper.findOneByAc(ac) != null) {
			return false;
		}

		Account account = new Account();
		account.setAc(ac);
		account.setPw("EN-"+pw);
		account.setRole("NORMAL");
		accountMapper.save(account);

		return true;
	}
}
