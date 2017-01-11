package com.example.mapper;

import org.apache.ibatis.annotations.Param;

import com.example.domain.Account;

public interface AccountMapper {
	public Account findOneByAc(@Param("ac") String ac);
	
	public void save(@Param("Account") Account ac);
}
