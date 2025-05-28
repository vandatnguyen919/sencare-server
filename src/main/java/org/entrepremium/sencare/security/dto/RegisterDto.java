package org.entrepremium.sencare.security.dto;

public record RegisterDto(
    String email,
    String password,
    String confirmPassword
) {
}
