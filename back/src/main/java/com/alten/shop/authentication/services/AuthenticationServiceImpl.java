package com.alten.shop.authentication.services;

import com.alten.shop.authentication.exceptions.AuthErrors;
import com.alten.shop.authentication.exceptions.AuthException;
import com.alten.shop.authentication.models.dtos.AuthenticationResponse;
import com.alten.shop.authentication.models.dtos.LoginRequest;
import com.alten.shop.authentication.models.dtos.RegisterRequest;
import com.alten.shop.cart.models.entities.Cart;
import com.alten.shop.cart.repositories.CartRepository;
import com.alten.shop.tokens.models.entities.Token;
import com.alten.shop.tokens.models.enums.TokenType;
import com.alten.shop.tokens.repositories.TokenRepository;
import com.alten.shop.tokens.services.JwtService;
import com.alten.shop.users.models.dtos.UserResponse;
import com.alten.shop.users.models.entities.User;
import com.alten.shop.users.repositories.UserRepository;
import com.alten.shop.utils.Tools;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final ModelMapper modelMapper;
  private final CartRepository cartRepository;

  @Override
  public AuthenticationResponse register(RegisterRequest registerRequest) {

    if (registerRequest.getFirstname() == null
        || registerRequest.getEmail() == null
        || registerRequest.getUsername() == null || registerRequest.getPassword() == null
        || registerRequest.getEmail().equals("") || registerRequest.getFirstname().equals("")
        || registerRequest.getUsername().equals("")
        || registerRequest.getPassword().equals("")) {
      throw new AuthException(AuthErrors.EMPTY_FIELDS);
    }

    if (Tools.isValidEmail(registerRequest.getEmail()) == false) {
      throw new AuthException(AuthErrors.INVALID_EMAIL);
    }

    Optional<User> queryUser = repository.findByEmail(registerRequest.getEmail());

    if (queryUser.isPresent()) {
      throw new AuthException(AuthErrors.USER_ALREADY_EXISTS);
    }

    User user = User.builder()
        .firstname(registerRequest.getFirstname())
        .password(passwordEncoder.encode(registerRequest.getPassword()))
        .email(registerRequest.getEmail())
        .createAt(LocalDateTime.now())
        .build();

    User savedUser = repository.save(user);


    Cart cart = new Cart();
    cart.setUser(savedUser);
    cartRepository.save(cart);
    
    

    String jwtToken = jwtService.generateToken(user);

    revokeAllUserTokens(savedUser);

    UserResponse userResponse = modelMapper.map(savedUser, UserResponse.class);

    saveUserToken(savedUser, jwtToken);

    AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
        .token(jwtToken)
        .user(userResponse)
        .build();

    return authenticationResponse;
  }

  @Override
  public AuthenticationResponse login(LoginRequest loginRequest) {

    if (loginRequest.getEmail() == null || loginRequest.getPassword() == null
        || loginRequest.getEmail().equals("")
        || loginRequest.getPassword().equals("")) {
      throw new AuthException(AuthErrors.EMPTY_FIELDS);
    }

    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
        loginRequest.getEmail(), loginRequest.getPassword());

    try {
      Authentication authentication = authenticationManager.authenticate(authToken);
      SecurityContextHolder.getContext().setAuthentication(authentication);
      User user = repository.findByEmail(loginRequest.getEmail())
          .orElseThrow(() -> new AuthException(AuthErrors.USER_NOT_FOUND));

      String jwtToken = jwtService.generateToken(user);
      revokeAllUserTokens(user);
      saveUserToken(user, jwtToken);

      UserResponse userResponse = modelMapper.map(user, UserResponse.class);

      AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
          .token(jwtToken)
          .user(userResponse)
          .build();

      return authenticationResponse;
    } catch (Exception ex) {
      log.info("Exception => {}", ex);
      throw new AuthException(AuthErrors.INVALID_CREDIDENTIALS);
    }
  }

  @Override
  public void saveUserToken(User user, String jwtToken) {
    Token token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  @Override
  public void revokeAllUserTokens(User user) {
    // On part du principe que l'utilisateur ne pas avoir qu'une seule session valide
    List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

}
