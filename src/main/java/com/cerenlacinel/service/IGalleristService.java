package com.cerenlacinel.service;

import com.cerenlacinel.dto.DtoGallerist;
import com.cerenlacinel.dto.DtoGalleristIU;

public interface IGalleristService {

	public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU);
}
