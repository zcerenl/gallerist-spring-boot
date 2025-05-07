package com.cerenlacinel.service.impl;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cerenlacinel.dto.DtoAddress;
import com.cerenlacinel.dto.DtoCar;
import com.cerenlacinel.dto.DtoGallerist;
import com.cerenlacinel.dto.DtoGalleristCar;
import com.cerenlacinel.dto.DtoGalleristCarIU;
import com.cerenlacinel.exception.BaseException;
import com.cerenlacinel.exception.ErrorMessage;
import com.cerenlacinel.exception.MessageType;
import com.cerenlacinel.model.Car;
import com.cerenlacinel.model.Gallerist;
import com.cerenlacinel.model.GalleristCar;
import com.cerenlacinel.repository.CarRepository;
import com.cerenlacinel.repository.GalleristCarRepository;
import com.cerenlacinel.repository.GalleristRepository;
import com.cerenlacinel.service.IGalleristCarService;

@Service
public class GalleristCarServiceImpl implements IGalleristCarService{

	@Autowired
	private GalleristCarRepository galleristCarRepository;
	
	@Autowired
	private GalleristRepository galleristRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	private GalleristCar createGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
		
		Optional<Gallerist> optGallerist = galleristRepository.findById(dtoGalleristCarIU.getGalleristId());
		if (optGallerist.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getGalleristId().toString()));
			
		}
		
		Optional<Car> optCar = carRepository.findById(dtoGalleristCarIU.getCarId());
		if (optCar.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getCarId().toString()));
		}
		
		GalleristCar galleristCar = new GalleristCar();
		galleristCar.setCreateTime(new Date(0));
		galleristCar.setGallerist(optGallerist.get());
		galleristCar.setCar(optCar.get());
		
		return galleristCar;
	}
	
	
	
	@Override
	public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
		DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
		DtoGallerist dtoGallerist = new DtoGallerist();
		DtoCar dtoCar = new DtoCar();
		
		DtoAddress dtoAddress = new DtoAddress();
		
		GalleristCar savedGalleristCar = galleristCarRepository.save(createGalleristCar(dtoGalleristCarIU));
		
		BeanUtils.copyProperties(savedGalleristCar, dtoGalleristCar);
		BeanUtils.copyProperties(savedGalleristCar.getGallerist(), dtoGallerist);
		BeanUtils.copyProperties(savedGalleristCar.getGallerist().getAddress(), dtoAddress);
		
		BeanUtils.copyProperties(savedGalleristCar.getCar(), dtoCar);
		
		dtoGallerist.setAddress(dtoAddress);
		dtoGalleristCar.setGallerist(dtoGallerist);
	    dtoGalleristCar.setCar(dtoCar);	
	    
		return dtoGalleristCar;
	}

}
