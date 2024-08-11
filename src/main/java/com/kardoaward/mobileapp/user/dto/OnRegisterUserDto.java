package com.kardoaward.mobileapp.user.dto;

import com.kardoaward.mobileapp.user.model.UserRoles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OnRegisterUserDto {

    private long id;
    private String email;
    private UserRoles role;
}
