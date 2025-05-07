package com.cerenlacinel.controller;

import com.cerenlacinel.dto.DtoGallerist;
import com.cerenlacinel.dto.DtoGalleristIU;

public interface IRestGalleristController {

	public RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU);
}
