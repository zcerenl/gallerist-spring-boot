package com.cerenlacinel.service;

import com.cerenlacinel.dto.DtoAccount;
import com.cerenlacinel.dto.DtoAccountIU;
import com.cerenlacinel.dto.DtoAddress;

public interface IAccountService {

	public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);
}
