package com.cerenlacinel.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cerenlacinel.controller.IRestCarController;
import com.cerenlacinel.controller.RestBaseController;
import com.cerenlacinel.controller.RootEntity;
import com.cerenlacinel.dto.DtoCar;
import com.cerenlacinel.dto.DtoCarIU;
import com.cerenlacinel.service.ICarService;

@RestController
@RequestMapping("/rest/api/car")
public class RestCarControllerImpl extends RestBaseController implements IRestCarController{

	@Autowired
	private ICarService carService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU) {
		return ok(carService.saveCar(dtoCarIU));
		
	}

}
