package org.entrepremium.sencare.feature.myuser.converter;

import org.entrepremium.sencare.feature.myuser.MyUser;
import org.entrepremium.sencare.feature.myuser.dto.UserDto;
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