package org.entrepremium.sencare.features.myuser.converter;

import org.entrepremium.sencare.features.myuser.MyUser;
import org.entrepremium.sencare.features.myuser.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUserConverter implements Converter<UserDto, MyUser> {

    @Override
    public MyUser convert(@NonNull UserDto userDto) {

        MyUser myUser = new MyUser();
        myUser.setId(userDto.id());
        myUser.setEmail(userDto.email());
        myUser.setEnabled(userDto.enabled());
        myUser.setRoles(userDto.roles());
        return myUser;
    }
}