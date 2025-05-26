package org.entrepremium.sencare.security.converter;

import org.entrepremium.sencare.features.myuser.MyUser;
import org.entrepremium.sencare.security.dto.RegisterDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RegisterDtoToUserConverter implements Converter<RegisterDto, MyUser> {

    @Override
    public MyUser convert(RegisterDto registerDto) {
        MyUser myUser = new MyUser();
        myUser.setEmail(registerDto.email());
        myUser.setPassword(registerDto.password());
        return myUser;
    }
}
