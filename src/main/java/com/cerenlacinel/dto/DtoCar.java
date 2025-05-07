package com.cerenlacinel.dto;

import java.math.BigDecimal;

import com.cerenlacinel.enums.CarStatusType;
import com.cerenlacinel.enums.CurrencyType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCar extends DtoBase{

	
private String plaka;
	
	private String brand;
	
	private String model;
	
	private Integer productionYear;
	
	private BigDecimal price;
	
	private CurrencyType currencyType;
	
	private BigDecimal damagePrice;
	
	private CarStatusType carStatusType;
}
