package com.cerenlacinel.service;

import com.cerenlacinel.dto.DtoCustomer;
import com.cerenlacinel.dto.DtoCustomerIU;

public interface ICustomerService {

	public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);
}
