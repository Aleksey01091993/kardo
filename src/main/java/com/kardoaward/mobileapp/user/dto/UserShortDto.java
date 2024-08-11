package com.kardoaward.mobileapp.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserShortDto {

    @Email(message = "Поле email должно быть в форме email@host.domen")
    @NotEmpty
    private String email;
    @NotEmpty(message = "Поле пароль не должно быть пустым")
    private String password;


}
