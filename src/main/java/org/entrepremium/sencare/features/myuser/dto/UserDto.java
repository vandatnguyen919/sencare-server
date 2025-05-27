package org.entrepremium.sencare.features.myuser.dto;

public record UserDto(String id,
                      String email,
                      boolean enabled,
                      String roles) {
}
