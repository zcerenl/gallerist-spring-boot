package com.cerenlacinel.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrenyRatesResponse {
	
	private Integer totalCount;
	
	private List<CurrencyRatesItems> items;
	

}
