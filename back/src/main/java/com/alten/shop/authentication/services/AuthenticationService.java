package com.alten.shop.authentication.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.alten.shop.authentication.models.dtos.AuthenticationResponse;
import com.alten.shop.authentication.models.dtos.LoginRequest;
import com.alten.shop.authentication.models.dtos.RegisterRequest;
import com.alten.shop.users.models.entities.User;



public interface AuthenticationService {
  public AuthenticationResponse register(RegisterRequest request);
  public AuthenticationResponse login(LoginRequest request);
  public void saveUserToken(User user, String jwtToken);
  public void revokeAllUserTokens(User user);
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response) throws IOException;

}
