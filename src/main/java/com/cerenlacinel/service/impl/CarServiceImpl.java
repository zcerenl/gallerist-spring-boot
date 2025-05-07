package com.cerenlacinel.service.impl;

import java.sql.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cerenlacinel.dto.DtoCar;
import com.cerenlacinel.dto.DtoCarIU;
import com.cerenlacinel.model.Car;
import com.cerenlacinel.repository.CarRepository;
import com.cerenlacinel.service.ICarService;

@Service
public class CarServiceImpl implements ICarService{

	@Autowired
	private CarRepository carRepository;
	
	
	private Car createCar(DtoCarIU dtoCarIU) {
		Car car = new Car();
		car.setCreateTime(new Date(0));
		
		BeanUtils.copyProperties(dtoCarIU, car);
		return car;
	}
	
	@Override
	public DtoCar saveCar(DtoCarIU dtoCarIU) {
		DtoCar dtoCar = new DtoCar();
	    Car savedCar =	carRepository.save(createCar(dtoCarIU));
		
	    BeanUtils.copyProperties(savedCar, dtoCar);
	    return dtoCar;
	}

}
