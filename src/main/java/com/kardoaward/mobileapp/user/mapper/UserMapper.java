package com.kardoaward.mobileapp.user.mapper;

import com.kardoaward.mobileapp.user.dto.OnRegisterUserDto;
import com.kardoaward.mobileapp.user.dto.UserDto;
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

    public static UserDto toUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName() != null? user.getFirstName() : null)
                .surname(user.getSurname() != null? user.getSurname() : null)
                .patronymic(user.getPatronymic() != null? user.getPatronymic() : null)
                .birthday(user.getBirthday() != null? user.getBirthday() : null)
                .sex(user.getSex() != null? user.getSex() : null)
                .direction(user.getDirection() != null? user.getDirection() : null)
                .phone(user.getPhone() != null? user.getPhone() : null)
                .socialMediaUrl(user.getSocialMediaUrl() != null? user.getPhone() : null)
                .portfolioUrl(user.getPortfolioUrl() != null? user.getPortfolioUrl() : null)
                .country(user.getCountry() != null? user.getCountry() : null)
                .city(user.getCity() != null? user.getCity() : null)
                .region(user.getRegion() != null? user.getRegion() : null)
                .build();
    }
}
