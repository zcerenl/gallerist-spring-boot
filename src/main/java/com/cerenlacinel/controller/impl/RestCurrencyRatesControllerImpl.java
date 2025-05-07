package com.cerenlacinel.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cerenlacinel.controller.IRestCurrencyRatesController;
import com.cerenlacinel.controller.RestBaseController;
import com.cerenlacinel.controller.RootEntity;
import com.cerenlacinel.dto.CurrenyRatesResponse;
import com.cerenlacinel.service.ICurrencyRatesService;

@RestController
@RequestMapping("/rest/api/")
public class RestCurrencyRatesControllerImpl extends RestBaseController implements IRestCurrencyRatesController{

	@Autowired
	private ICurrencyRatesService currencyRatesService;
	
	@GetMapping("/currency-rates")
	@Override
	public RootEntity<CurrenyRatesResponse> getCurrencyRates(
			@RequestParam("startDate") String startDate, @RequestParam("startDate") String endDate) {
		return ok(currencyRatesService.getCurrencyRates(startDate, endDate));
	}

}
