package com.cerenlacinel.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoUser extends DtoBase{

	private String username;
	
	private String password;
}
