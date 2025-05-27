package com.alten.shop.authentication.services;


import com.alten.shop.authentication.models.dtos.AuthenticationResponse;
import com.alten.shop.authentication.models.dtos.LoginRequest;
import com.alten.shop.authentication.models.dtos.RegisterRequest;
import com.alten.shop.users.models.entities.User;



public interface AuthenticationService {
  public AuthenticationResponse register(RegisterRequest request);
  public AuthenticationResponse login(LoginRequest request);
  public void saveUserToken(User user, String jwtToken);
  public void revokeAllUserTokens(User user);
}
