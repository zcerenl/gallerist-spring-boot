package com.cerenlacinel.controller.impl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cerenlacinel.controller.IRestGalleristController;
import com.cerenlacinel.controller.RestBaseController;
import com.cerenlacinel.controller.RootEntity;
import com.cerenlacinel.dto.DtoGallerist;
import com.cerenlacinel.dto.DtoGalleristIU;
import com.cerenlacinel.service.IGalleristService;
import com.cerenlacinel.service.impl.GalleristServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/gallerist")
public class RestGalleristControllerImpl extends RestBaseController implements IRestGalleristController{

	private IGalleristService galleristService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoGallerist> saveGallerist(@Valid @RequestBody DtoGalleristIU dtoGalleristIU){
		return ok(galleristService.saveGallerist(dtoGalleristIU));
		
		
	}
}
