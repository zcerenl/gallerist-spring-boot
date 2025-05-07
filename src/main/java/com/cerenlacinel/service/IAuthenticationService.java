package com.cerenlacinel.service;


import com.cerenlacinel.dto.AuthRequest;
import com.cerenlacinel.dto.AuthResponse;
import com.cerenlacinel.dto.DtoUser;
import com.cerenlacinel.dto.RefreshTokenRequest;
import com.cerenlacinel.repository.RefreshTokenRepository;

public interface IAuthenticationService {
	
	public DtoUser register(AuthRequest input);
	
	public AuthResponse authenticate(AuthRequest input);
	
	public AuthResponse refreshToken(RefreshTokenRequest input);
}
