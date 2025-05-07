package com.cerenlacinel.service;

import com.cerenlacinel.dto.DtoAddress;
import com.cerenlacinel.dto.DtoAddressIU;

public interface IAddressService {

	public DtoAddress saveAddress(DtoAddressIU droAddressIU);
}
