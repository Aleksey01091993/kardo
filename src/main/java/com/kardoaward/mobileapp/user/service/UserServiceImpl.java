package com.kardoaward.mobileapp.user.service;

import com.kardoaward.mobileapp.config.UserDetailsImpl;
import com.kardoaward.mobileapp.exceptions.NotFoundException;
import com.kardoaward.mobileapp.exceptions.UserAlreadyExistsException;
import com.kardoaward.mobileapp.user.dto.UserShortDto;
import com.kardoaward.mobileapp.user.mapper.UserMapper;
import com.kardoaward.mobileapp.user.model.User;
import com.kardoaward.mobileapp.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return UserDetailsImpl.build(findByEmail(username));
    }

    private final UserRepository userRepository;


    @Override
    public User findByEmail(String email) {
        log.info("Finding user by email: {}", email);
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(String.format("Пользователь с email '%s' не найден", email))
        );
    }

    @Override
    @Transactional
    public User create(UserShortDto userShortDto) {
        log.info("Creating user: {}", userShortDto);
        User user = UserMapper.fromUserShortDto(userShortDto);
        return userRepository.save(user);
    }

    @Override
    public User getUserById(long id) {
        log.info("Getting user by id: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Пользователь с таким id не найден."));
    }

    @Override
    @Transactional
    public User update(long id, User user) {
        log.info("Updating user: {}", user);
        User oldUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Пользователь с таким id не найден."));
        if (user.getPassword() != null) {
            oldUser.setPassword(user.getPassword());
        }
        if (user.getEmail() != null) {
            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                throw new UserAlreadyExistsException("Пользователь с таким email уже существует");
            } else {
                oldUser.setEmail(user.getEmail());
            }
        }
        if (user.getFirstName() != null) {
            oldUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            oldUser.setLastName(user.getLastName());
        }
        if (user.getSurname() != null) {
            oldUser.setSurname(user.getSurname());
        }
        if (user.getPhone() != null) {
            oldUser.setPhone(user.getPhone());
        }
        if (user.getCity() != null) {
            oldUser.setCity(user.getCity());
        }
        if (user.getCountry() != null) {
            oldUser.setCountry(user.getCountry());
        }
        if (user.getPortfolioUrl() != null) {
            oldUser.setPortfolioUrl(user.getPortfolioUrl());
        }
        if (user.getSocialMediaUrl() != null) {
            oldUser.setSocialMediaUrl(user.getSocialMediaUrl());
        }
        if (user.getBirthday() != null) {
            oldUser.setBirthday(user.getBirthday());
        }
        return userRepository.save(oldUser);
    }
}
