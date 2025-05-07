package com.cerenlacinel.controller;

import com.cerenlacinel.dto.DtoCar;
import com.cerenlacinel.dto.DtoCarIU;

public interface IRestCarController {

	public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);
}
