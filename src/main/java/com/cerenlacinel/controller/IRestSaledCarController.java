package com.cerenlacinel.controller;

import com.cerenlacinel.dto.DtoSaledCar;
import com.cerenlacinel.dto.DtoSaledCarIU;

public interface IRestSaledCarController {

	public RootEntity<DtoSaledCar> buyCar(DtoSaledCarIU dtoSaledCarIU);
}
