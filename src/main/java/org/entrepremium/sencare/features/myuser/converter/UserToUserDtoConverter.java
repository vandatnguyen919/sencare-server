package org.entrepremium.sencare.features.myuser.converter;

import org.entrepremium.sencare.features.myuser.MyUser;
import org.entrepremium.sencare.features.myuser.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDtoConverter implements Converter<MyUser, UserDto> {

    @Override
    public UserDto convert(@NonNull MyUser source) {
        return new UserDto(
                source.getId(),
                source.getEmail(),
                source.isEnabled(),
                source.getRoles(),
                source.getCreatedAt(),
                source.getUpdatedAt()
        );
    }
}