package com.cerenlacinel.service;

import com.cerenlacinel.dto.DtoSaledCar;
import com.cerenlacinel.dto.DtoSaledCarIU;

public interface ISaledCarService {

	public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU);
}
