package com.kardoaward.mobileapp.user.mapper;

import com.kardoaward.mobileapp.user.dto.OnRegisterUserDto;
import com.kardoaward.mobileapp.user.dto.UserShortDto;
import com.kardoaward.mobileapp.user.model.User;

public class UserMapper {

    public static User fromUserShortDto(UserShortDto userShortDto) {
        User user = new User();
        user.setEmail(userShortDto.getEmail());
        user.setPassword(userShortDto.getPassword());
        return user;
    }

    public static OnRegisterUserDto toOnRegisterUserDto(User user) {
        OnRegisterUserDto onRegisterUserDto = new OnRegisterUserDto();
        onRegisterUserDto.setId(user.getId());
        onRegisterUserDto.setEmail(user.getEmail());
        onRegisterUserDto.setRole(user.getRole());
        return onRegisterUserDto;
    }
}
