package com.cerenlacinel.service.impl;

import java.net.http.HttpHeaders;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cerenlacinel.controller.RestBaseController;
import com.cerenlacinel.dto.CurrenyRatesResponse;
import com.cerenlacinel.exception.BaseException;
import com.cerenlacinel.exception.ErrorMessage;
import com.cerenlacinel.exception.MessageType;
import com.cerenlacinel.service.ICurrencyRatesService;

@Service
public class CurrencyRatesServiceImpl implements ICurrencyRatesService{

	@Override
	public CurrenyRatesResponse getCurrencyRates(String startDate, String endDate) {
		String rootURL = "https://evds2.tcmb.gov.tr/service/evds/";
		String series = "TP.DK.USD.A";
		String type = "json";
		
		String endpoint = rootURL+"series="+series+"&startDate="+startDate+"&endDate="+endDate+"&type="+type;
		
		org.springframework.http.HttpHeaders httpHeaders = new org.springframework.http.HttpHeaders();
		httpHeaders.set("key", "NV0340rGHL");
			
		HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
		
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			
			ResponseEntity<CurrenyRatesResponse> response = restTemplate.exchange(endpoint, HttpMethod.GET,httpEntity, new ParameterizedTypeReference<CurrenyRatesResponse>() {
			});
			if (response.getStatusCode().is2xxSuccessful()) {
			  return response.getBody();
			}
		} catch (Exception e) {
			throw new BaseException(new ErrorMessage(MessageType.CURRENY_RATES_IS_OCCURED, e.getMessage()));

		}
		return null;
	
	}
}
