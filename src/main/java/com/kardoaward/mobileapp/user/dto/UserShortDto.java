package com.kardoaward.mobileapp.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserShortDto {

    @Email(message = "Поле email должно быть в форме email@host.domen")
    @NotEmpty(message = "Поле email не может быть пустым")
    @Pattern(regexp = "^[a-zA-Z0-9@._-]+$", message = "Поле email не может быть пустым," +
            " и должно включать только буквы латинского алфавита, цифры и символы ._-")
    private String email;
    @NotEmpty(message = "Поле пароль не должно быть пустым")
    @Size(min = 8, max = 64, message = "Длина пароля должна быть не менее 8 и не более 64 символов")
    private String password;
}
