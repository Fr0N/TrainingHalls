package com.traininghalls.areas.users.services;

import com.traininghalls.areas.users.entities.User;
import com.traininghalls.areas.users.models.RegisterUserBindingModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    boolean register(RegisterUserBindingModel registerUserBindingModel);

    boolean userExists(String username);

    User findOneByUsername(String username);
}
