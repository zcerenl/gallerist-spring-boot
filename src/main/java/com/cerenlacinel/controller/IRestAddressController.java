package com.cerenlacinel.controller;

import com.cerenlacinel.dto.DtoAddress;
import com.cerenlacinel.dto.DtoAddressIU;

public interface IRestAddressController {

	public RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);
}
