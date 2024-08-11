package com.kardoaward.mobileapp.user.service;

import com.kardoaward.mobileapp.config.UserDetailsImpl;
import com.kardoaward.mobileapp.exceptions.AuthException;
import com.kardoaward.mobileapp.exceptions.UserAlreadyExistsException;
import com.kardoaward.mobileapp.user.dto.UserShortDto;
import com.kardoaward.mobileapp.user.mapper.UserMapper;
import com.kardoaward.mobileapp.user.model.User;
import com.kardoaward.mobileapp.user.model.UserRoles;
import com.kardoaward.mobileapp.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
        if (userRepository.findByEmail(userShortDto.getEmail()).isPresent()) {
            throw new AuthException("Пользователь с таким email уже зарегистрирован");
        }
        User user = UserMapper.fromUserShortDto(userShortDto);
        if (Objects.equals(user.getEmail(), "admin@test.ru")) {
            user.setRole(UserRoles.ROLE_ADMIN);
        }
        if (Objects.equals(user.getEmail(), "expert@test.ru")) {
            user.setRole(UserRoles.ROLE_EXPERT);
        }
        return userRepository.save(user);
    }

    @Override
    public User getUser() {
        User currentUser = getUserByAuthentication();
        log.info("Getting user by id: {}", currentUser.getId());
        return currentUser;
    }

    @Override
    @Transactional
    public User update(User user) {
        log.info("Updating user: {}", user);
        User oldUser = getUserByAuthentication();
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
        if (user.getPatronymic() != null) {
            oldUser.setPatronymic(user.getPatronymic());
        }
        if (user.getSurname() != null) {
            oldUser.setSurname(user.getSurname());
        }
        if (user.getDirection() != null) {
            oldUser.setDirection(user.getDirection());
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
        if (user.getRegion() != null) {
            oldUser.setRegion(user.getRegion());
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

    @Override
    public User getUserByAuthentication() {
        UserDetailsImpl udi = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return udi.getUser();
    }
}
