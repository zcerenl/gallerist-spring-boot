package com.cerenlacinel.controller;

import com.cerenlacinel.dto.DtoGalleristCar;
import com.cerenlacinel.dto.DtoGalleristCarIU;

public interface IRestGalleristCarController {

	public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
}
