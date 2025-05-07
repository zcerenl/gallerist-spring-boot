package com.cerenlacinel.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cerenlacinel.controller.IRestAccountController;
import com.cerenlacinel.controller.RestBaseController;
import com.cerenlacinel.controller.RootEntity;
import com.cerenlacinel.dto.DtoAccount;
import com.cerenlacinel.dto.DtoAccountIU;
import com.cerenlacinel.service.IAccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/account")
public class RestAccountControllerImpl extends RestBaseController implements IRestAccountController{

	@Autowired
	private IAccountService accountService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoAccount> saveAccount(@Valid @RequestBody DtoAccountIU dtoAccountIU) {
		return ok(accountService.saveAccount(dtoAccountIU));
	}
	

}
