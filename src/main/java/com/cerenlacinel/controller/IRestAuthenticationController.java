package com.cerenlacinel.controller;

import com.cerenlacinel.dto.AuthRequest;
import com.cerenlacinel.dto.AuthResponse;
import com.cerenlacinel.dto.DtoUser;
import com.cerenlacinel.dto.RefreshTokenRequest;

public interface IRestAuthenticationController {

	
	public RootEntity<DtoUser> register(AuthRequest input);
	
	public RootEntity<AuthResponse> authenticate(AuthRequest input);
	
	public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest input);
}
