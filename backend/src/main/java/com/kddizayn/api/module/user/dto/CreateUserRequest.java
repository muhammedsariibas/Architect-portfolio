package com.kddizayn.api.module.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {

    @NotBlank(message = "Kullanıcı adı zorunludur")
    @Size(min = 3, max = 50, message = "Kullanıcı adı 3-50 karakter arasında olmalıdır")
    private String username;

    @NotBlank(message = "Şifre zorunludur")
    @Size(min = 8, max = 100, message = "Şifre en az 8 karakter olmalıdır")
    private String password;
}
