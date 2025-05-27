package com.alten.shop.authentication.services;

import com.alten.shop.authentication.models.dtos.AuthenticationResponse;
import com.alten.shop.authentication.models.dtos.LoginRequest;
import com.alten.shop.authentication.models.dtos.RegisterRequest;
import com.alten.shop.users.models.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {@Override
  public AuthenticationResponse register(RegisterRequest request) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'register'");
  }

  @Override
  public AuthenticationResponse login(LoginRequest request) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'login'");
  }

  @Override
  public void saveUserToken(User user, String jwtToken) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'saveUserToken'");
  }

  @Override
  public void revokeAllUserTokens(User user) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'revokeAllUserTokens'");
  }

  @Override
  public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'refreshToken'");
  }


}
