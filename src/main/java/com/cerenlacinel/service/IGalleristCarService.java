package com.cerenlacinel.service;

import com.cerenlacinel.dto.DtoGalleristCar;
import com.cerenlacinel.dto.DtoGalleristCarIU;

public interface IGalleristCarService {

	public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
}
