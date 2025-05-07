package com.cerenlacinel.service;

import com.cerenlacinel.dto.DtoCar;
import com.cerenlacinel.dto.DtoCarIU;

public interface ICarService {

	public DtoCar saveCar(DtoCarIU dtoCarIU);
}
