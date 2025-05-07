package com.cerenlacinel.controller.impl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cerenlacinel.controller.IRestAddressController;
import com.cerenlacinel.controller.RestBaseController;
import com.cerenlacinel.controller.RootEntity;
import com.cerenlacinel.dto.DtoAddress;
import com.cerenlacinel.dto.DtoAddressIU;
import com.cerenlacinel.service.IAddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/address")
public class RestAddressControllerImpl extends RestBaseController implements IRestAddressController{

	private IAddressService addressService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoAddress> saveAddress(@Valid @RequestBody DtoAddressIU dtoAddressIU) {
		return ok(addressService.saveAddress(dtoAddressIU));
	}

}
