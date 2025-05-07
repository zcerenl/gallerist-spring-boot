package com.cerenlacinel.service.impl;

import java.sql.Date;
import java.util.Optional;
import org.apache.juli.DateFormatCache;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cerenlacinel.dto.DtoAddress;
import com.cerenlacinel.dto.DtoGallerist;
import com.cerenlacinel.dto.DtoGalleristIU;
import com.cerenlacinel.exception.BaseException;
import com.cerenlacinel.exception.ErrorMessage;
import com.cerenlacinel.exception.MessageType;
import com.cerenlacinel.model.Address;
import com.cerenlacinel.model.Gallerist;
import com.cerenlacinel.repository.AddressRepository;
import com.cerenlacinel.repository.GalleristRepository;
import com.cerenlacinel.service.IGalleristService;

@Service
public class GalleristServiceImpl implements IGalleristService{

	@Autowired
	private GalleristRepository galleristRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	
	private Gallerist createGallerist(DtoGalleristIU dtoGalleristIU) {
		Optional<Address> optAddress = addressRepository.findById(dtoGalleristIU.getAddressId());
		if (optAddress.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristIU.getAddressId().toString()));
		}
		
		Gallerist gallerist = new Gallerist();
		gallerist.setCreateTime(new Date(0));
		
		BeanUtils.copyProperties(dtoGalleristIU, gallerist);
		gallerist.setAddress(optAddress.get());
		
		return gallerist;
	}
	
	
	@Override
	public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU) {
		DtoGallerist dtoGallerist = new DtoGallerist();
		DtoAddress dtoAddress = new DtoAddress();
		
		Gallerist savedGallerist = galleristRepository.save(createGallerist(dtoGalleristIU));
		
		BeanUtils.copyProperties(savedGallerist, dtoGallerist);
		BeanUtils.copyProperties(savedGallerist.getAddress() , dtoAddress);
		
		dtoGallerist.setAddress(dtoAddress);
		return dtoGallerist;
	}

}
