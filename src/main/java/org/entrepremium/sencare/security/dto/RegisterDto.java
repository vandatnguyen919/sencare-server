package org.entrepremium.sencare.security.dto;

import org.entrepremium.sencare.features.myuser.MyUser;

public record RegisterDto(
    String email,
    String password,
    String confirmPassword
) {
}
