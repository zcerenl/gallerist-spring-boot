package com.cerenlacinel.service;

import com.cerenlacinel.dto.CurrenyRatesResponse;

public interface ICurrencyRatesService {

	public CurrenyRatesResponse getCurrencyRates(String startDateString, String endDate);
}
