package com.cerenlacinel.service.impl;

import java.sql.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cerenlacinel.dto.DtoAccount;
import com.cerenlacinel.dto.DtoAccountIU;
import com.cerenlacinel.model.Account;
import com.cerenlacinel.repository.AccountRepository;
import com.cerenlacinel.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService{

	@Autowired
	private AccountRepository accountRepository;
	
	private Account creatAccount(DtoAccountIU dtoAccountIU) {
		Account account = new Account();
		account.setCreateTime(new Date(0));
		
		BeanUtils.copyProperties(dtoAccountIU, account);
		return account;
	}
	
	
	@Override
	public DtoAccount saveAccount(DtoAccountIU dtoAccountIU) {
		DtoAccount dtoAccount = new DtoAccount();
		
		Account savedAccount = accountRepository.save(creatAccount(dtoAccountIU));
		BeanUtils.copyProperties(savedAccount, dtoAccount);
		return dtoAccount;
	}

}
