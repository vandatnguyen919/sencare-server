package org.entrepremium.sencare.security;


import jakarta.transaction.Transactional;
import org.entrepremium.sencare.features.myuser.MyUser;
import org.entrepremium.sencare.features.myuser.UserService;
import org.entrepremium.sencare.security.converter.RegisterDtoToUserConverter;
import org.entrepremium.sencare.security.dto.LoginDto;
import org.entrepremium.sencare.security.dto.RegisterDto;
import org.entrepremium.sencare.system.exception.PasswordIllegalArgumentException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class AuthService {

    private final JwtProvider jwtProvider;

    private final AuthenticationManager authenticationManager;

    private final RegisterDtoToUserConverter registerDtoToUserConverter;

    private final UserService userService;

    public AuthService(JwtProvider jwtProvider, AuthenticationManager authenticationManager, RegisterDtoToUserConverter registerDtoToUserConverter, UserService userService) {
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
        this.registerDtoToUserConverter = registerDtoToUserConverter;
        this.userService = userService;
    }

    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.email(), loginDto.password()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtProvider.createToken(authentication);
    }

    public void register(RegisterDto registerDto) {

        // If the new password and confirm new password do not match, throw an exception.
        if (!registerDto.password().equals(registerDto.confirmPassword())) {
            throw new PasswordIllegalArgumentException("Password and confirm password do not match.");
        }

//        // The new password must contain at least one digit, one lowercase letter, one uppercase letter, and be at least 8 characters long.
//        String passwordPolicy = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
//        if (!newPassword.matches(passwordPolicy)) {
//            throw new PasswordChangeIllegalArgumentException("New password does not conform to password policy.");
//        }

        MyUser newUser = registerDtoToUserConverter.convert(registerDto);
        newUser.setEnabled(true);
        newUser.setRoles("user");

        this.userService.save(newUser);
    }
}
