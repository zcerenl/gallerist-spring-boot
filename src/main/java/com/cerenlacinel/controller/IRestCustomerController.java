package com.cerenlacinel.controller;

import com.cerenlacinel.dto.DtoCustomer;
import com.cerenlacinel.dto.DtoCustomerIU;

public interface IRestCustomerController {

	public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);
}
