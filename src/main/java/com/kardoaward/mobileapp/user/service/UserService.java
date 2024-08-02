package com.kardoaward.mobileapp.user.service;

import com.kardoaward.mobileapp.user.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);
    User create(User user);
    User getUserById(long id);
}
