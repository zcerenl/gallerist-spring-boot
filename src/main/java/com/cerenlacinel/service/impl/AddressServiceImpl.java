package com.cerenlacinel.service.impl;

import java.sql.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cerenlacinel.dto.DtoAddress;
import com.cerenlacinel.dto.DtoAddressIU;
import com.cerenlacinel.model.Address;
import com.cerenlacinel.repository.AddressRepository;
import com.cerenlacinel.service.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService{

	@Autowired
	private AddressRepository addressRepository;
	
	private Address createAddress(DtoAddressIU dtoAddressIU) {
		Address address = new Address();
		address.setCreateTime(new Date(0));
		
		BeanUtils.copyProperties(dtoAddressIU, address);
		return address;
	}
	
	@Override
	public DtoAddress saveAddress(DtoAddressIU droAddressIU) {
		DtoAddress dtoAddress = new DtoAddress();
		
		Address savedAddress = addressRepository.save(createAddress(droAddressIU));
		BeanUtils.copyProperties(savedAddress, dtoAddress);
		return dtoAddress;
	}
	
	

}
