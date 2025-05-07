package com.cerenlacinel.controller;

import com.cerenlacinel.dto.DtoAccount;
import com.cerenlacinel.dto.DtoAccountIU;

public interface IRestAccountController {

	public RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);
}
