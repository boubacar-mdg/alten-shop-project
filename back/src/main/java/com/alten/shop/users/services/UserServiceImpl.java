package com.alten.shop.users.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alten.shop.users.exceptions.UserErros;
import com.alten.shop.users.exceptions.UserException;
import com.alten.shop.users.models.entities.User;
import com.alten.shop.users.repositories.UserRepository;

import jakarta.transaction.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    
    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UserException(UserErros.USER_NOT_FOUND));
        return user;

    }

}
