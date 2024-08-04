package com.kardoaward.mobileapp.user.service;

import com.kardoaward.mobileapp.user.dto.UserShortDto;
import com.kardoaward.mobileapp.user.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User create(UserShortDto userShortDto);

    User getUserById(long id);

    User update(long id, User user);
}
