package org.entrepremium.sencare.security.converter;

import org.entrepremium.sencare.feature.myuser.MyUser;
import org.entrepremium.sencare.security.dto.RegisterDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class RegisterDtoToUserConverter implements Converter<RegisterDto, MyUser> {

    @Override
    public MyUser convert(@NonNull RegisterDto source) {
        MyUser myUser = new MyUser();
        myUser.setEmail(source.email());
        myUser.setPassword(source.password());
        return myUser;
    }
}
