package com.kardoaward.mobileapp.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kardoaward.mobileapp.user.model.UserDirections;
import com.kardoaward.mobileapp.user.model.UserSex;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private Long id;
    private String firstName;
    private String surname;
    private String patronymic;
    private String email;
    private UserSex sex;
    private LocalDate birthday;
    private UserDirections direction;
    private String phone;
    private String socialMediaUrl;
    private String portfolioUrl;
    private String country;
    private String city;
    private String region;

}