package com.alten.shop.tokens.services;

import io.jsonwebtoken.Claims;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface JwtService {

  public String extractUsername(String token);

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
  public String generateToken(UserDetails userDetails);

  public String generateToken(
      Map<String, Object> extraClaims,
      UserDetails userDetails
  );

  public String generateRefreshToken(
      UserDetails userDetails
  );

  public boolean isTokenValid(String token, UserDetails userDetails);
  

}
