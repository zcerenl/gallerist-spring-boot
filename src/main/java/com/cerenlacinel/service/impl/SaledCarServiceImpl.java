package com.cerenlacinel.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cerenlacinel.dto.CurrenyRatesResponse;
import com.cerenlacinel.dto.DtoCar;
import com.cerenlacinel.dto.DtoCustomer;
import com.cerenlacinel.dto.DtoGallerist;
import com.cerenlacinel.dto.DtoSaledCar;
import com.cerenlacinel.dto.DtoSaledCarIU;
import com.cerenlacinel.enums.CarStatusType;
import com.cerenlacinel.exception.BaseException;
import com.cerenlacinel.exception.ErrorMessage;
import com.cerenlacinel.exception.MessageType;
import com.cerenlacinel.model.Car;
import com.cerenlacinel.model.Customer;
import com.cerenlacinel.model.SaledCar;
import com.cerenlacinel.repository.CarRepository;
import com.cerenlacinel.repository.CustomerRepository;
import com.cerenlacinel.repository.GalleristRepository;
import com.cerenlacinel.repository.SaledCarRepository;
import com.cerenlacinel.service.ICurrencyRatesService;
import com.cerenlacinel.service.ISaledCarService;
import com.cerenlacinel.utils.DateUtils;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class SaledCarServiceImpl implements ISaledCarService{

	@Autowired
	private SaledCarRepository saledCarRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private GalleristRepository galleristRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private ICurrencyRatesService currencyRatesService;
	
	
	
	public BigDecimal convertCustomerAmountToUSD(Customer customer) {
		
		CurrenyRatesResponse currenyRatesResponse = currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date(0)), DateUtils.getCurrentDate(new Date(0)));
		BigDecimal usd = new BigDecimal(currenyRatesResponse.getItems().get(0).getUsd());
		
		BigDecimal customerUSDAmount = customer.getAccount().getAmount().divide(usd, 2, RoundingMode.HALF_UP);
		return customerUSDAmount;
	}
	
	public boolean checkCarStatus(Long carId) {
		Optional<Car> optCar = carRepository.findById(carId);
		if (optCar.isPresent() && optCar.get().getCarStatusType().name().equals(CarStatusType.SALED.name())) {
			return false;
		}
		return true;
	}
	
	public BigDecimal remaningCustomerAmount(Customer customer , Car car) {
		BigDecimal customerUSDAmount = convertCustomerAmountToUSD(customer);
		BigDecimal remaningCustomerUSDAmount = customerUSDAmount.subtract(car.getPrice());
		
		CurrenyRatesResponse currenyRatesResponse = currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date(0)), DateUtils.getCurrentDate(new Date(0)));
		BigDecimal usd = new BigDecimal(currenyRatesResponse.getItems().get(0).getUsd());
		
		return remaningCustomerUSDAmount.multiply(usd);
		
	}
	
	
	public boolean checkAmount(DtoSaledCarIU dtoSaledCarIU) {
		
		Optional<Customer> optCustomer = customerRepository.findById(dtoSaledCarIU.getCustomerId());
		if (optCustomer.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCustomerId().toString()));
		}
		
		Optional<Car> optCar = carRepository.findById(dtoSaledCarIU.getCarId());
		if (optCar.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCarId().toString()));
		}
		
		BigDecimal customerUSDAmount = convertCustomerAmountToUSD(optCustomer.get());
		
		if (customerUSDAmount.compareTo(optCar.get().getPrice())==0 || customerUSDAmount.compareTo(optCar.get().getPrice())>0) {
			return true;
		}
		return false;
	}
	
	
	private SaledCar createSaledCar(DtoSaledCarIU dtoSaledCarIU) {
		SaledCar saledCar = new SaledCar();
		saledCar.setCreateTime(new Date(0));
		
		saledCar.setCustomer(customerRepository.findById(dtoSaledCarIU.getCustomerId()).orElse(null));
		saledCar.setGallerist(galleristRepository.findById(dtoSaledCarIU.getGalleristId()).orElse(null));
		saledCar.setCar(carRepository.findById(dtoSaledCarIU.getCarId()).orElse(null));
		
		return saledCar;
	}
	
	
	@Override
	public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU) {
		if(!checkAmount(dtoSaledCarIU)) {
			throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH, ""));
			
		}
		
		if (!checkCarStatus(dtoSaledCarIU.getCarId())) {
			throw new BaseException(new ErrorMessage(MessageType.CAR_STATUS_IS_ALREADY_SALED, dtoSaledCarIU.getCarId().toString()));
		}
		
		SaledCar saveSaledCar = saledCarRepository.save(createSaledCar(dtoSaledCarIU));
				
		Car car = saveSaledCar.getCar();
		car.setCarStatusType(CarStatusType.SALED);
		carRepository.save(car);
		
		Customer customer = saveSaledCar.getCustomer();
		customer.getAccount().setAmount(remaningCustomerAmount(customer, car));
		customerRepository.save(customer);
		
		return toDTO(saveSaledCar);
	}
	
	
	
	public DtoSaledCar toDTO(SaledCar saledCar) {
		DtoSaledCar dtoSaledCar = new DtoSaledCar();
		DtoCustomer dtoCustomer = new DtoCustomer();
		DtoGallerist dtoGallerist = new DtoGallerist();
		DtoCar dtoCar = new DtoCar();
		
		BeanUtils.copyProperties(saledCar, dtoSaledCar);
		BeanUtils.copyProperties(saledCar.getCustomer(), dtoCustomer);
		BeanUtils.copyProperties(saledCar.getGallerist(), dtoGallerist);
		BeanUtils.copyProperties(saledCar.getCar(), dtoCar);
		
		dtoSaledCar.setCustomer(dtoCustomer);
		dtoSaledCar.setGallerist(dtoGallerist);
		dtoSaledCar.setCar(dtoCar);
		return dtoSaledCar;
	}
	

}
