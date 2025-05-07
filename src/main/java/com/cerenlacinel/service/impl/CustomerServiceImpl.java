package com.cerenlacinel.service.impl;

import java.awt.image.ImageConsumer;
import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cerenlacinel.dto.DtoAccount;
import com.cerenlacinel.dto.DtoAddress;
import com.cerenlacinel.dto.DtoCustomer;
import com.cerenlacinel.dto.DtoCustomerIU;
import com.cerenlacinel.exception.BaseException;
import com.cerenlacinel.exception.ErrorMessage;
import com.cerenlacinel.exception.MessageType;
import com.cerenlacinel.model.Account;
import com.cerenlacinel.model.Address;
import com.cerenlacinel.model.Customer;
import com.cerenlacinel.repository.AccountRepository;
import com.cerenlacinel.repository.AddressRepository;
import com.cerenlacinel.repository.CustomerRepository;
import com.cerenlacinel.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private AccountRepository accountRepository;

	private Customer createCustomer(DtoCustomerIU dtoCustomerIU){
		Optional<Address>optAddress = addressRepository.findById(dtoCustomerIU.getAddressId());
		if (optAddress.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAddressId().toString()));
		}
		
		Optional<Account>optAccount = accountRepository.findById(dtoCustomerIU.getAccountId());
		if (optAccount.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAccountId().toString()));
		}
		
		Customer customer = new Customer();
		customer.setCreateTime(new Date(0));
		BeanUtils.copyProperties(dtoCustomerIU, customer);
		
		customer.setAddress(optAddress.get());
		customer.setAccount(optAccount.get());
		
		return customer;
	}
	
	@Override
	public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {
		DtoCustomer dtoCustomer = new DtoCustomer();
		DtoAddress dtoAddress = new DtoAddress();
		DtoAccount dtoAccount = new DtoAccount();
		
		Customer savedCustomer = customerRepository.save(createCustomer(dtoCustomerIU));
		
		BeanUtils.copyProperties(savedCustomer, dtoCustomer);
		BeanUtils.copyProperties(savedCustomer.getAddress(), dtoAddress);
		BeanUtils.copyProperties(savedCustomer.getAccount(), dtoAccount);
		
		dtoCustomer.setAddress(dtoAddress);
		dtoCustomer.setAccount(dtoAccount);
		
		return dtoCustomer;
	}

}
