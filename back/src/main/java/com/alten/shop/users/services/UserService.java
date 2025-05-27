package com.alten.shop.users.services;

import com.alten.shop.users.models.entities.User;

public interface UserService {
    public User getAuthenticatedUser();
}
