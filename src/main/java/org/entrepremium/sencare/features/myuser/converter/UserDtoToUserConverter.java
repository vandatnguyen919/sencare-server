package org.entrepremium.sencare.features.myuser.converter;

import org.entrepremium.sencare.features.myuser.MyUser;
import org.entrepremium.sencare.features.myuser.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUserConverter implements Converter<UserDto, MyUser> {

    @Override
    public MyUser convert(@NonNull UserDto source) {

        MyUser myUser = new MyUser();
        myUser.setId(source.id());
        myUser.setEmail(source.email());
        myUser.setEnabled(source.enabled());
        myUser.setRoles(source.roles());
        return myUser;
    }
}