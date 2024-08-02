package com.kardoaward.mobileapp.user.service;

import com.kardoaward.mobileapp.config.SecurityConfigurator;
import com.kardoaward.mobileapp.config.UserDetailsImpl;
import com.kardoaward.mobileapp.user.model.User;
import com.kardoaward.mobileapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return UserDetailsImpl.build(findByEmail(username));
    }

    private final UserRepository userRepository;


    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(String.format("Пользователь с email '%s' не найден", email))
        );
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User getUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow();
    }
}
