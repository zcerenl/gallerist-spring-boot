package com.cerenlacinel.controller;

import com.cerenlacinel.dto.CurrenyRatesResponse;

public interface IRestCurrencyRatesController {

	public RootEntity<CurrenyRatesResponse> getCurrencyRates(String startDate, String endDate); 
}
