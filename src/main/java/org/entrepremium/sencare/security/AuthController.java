package org.entrepremium.sencare.security;

import lombok.RequiredArgsConstructor;
import org.entrepremium.sencare.feature.myuser.MyUser;
import org.entrepremium.sencare.feature.myuser.UserService;
import org.entrepremium.sencare.feature.myuser.converter.UserToUserDtoConverter;
import org.entrepremium.sencare.feature.myuser.dto.UserDto;
import org.entrepremium.sencare.security.dto.LoginDto;
import org.entrepremium.sencare.security.dto.RegisterDto;
import org.entrepremium.sencare.system.Result;
import org.entrepremium.sencare.system.StatusCode;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("${api.endpoint.base-url}/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final UserService userService;

    private final UserToUserDtoConverter userToUserDtoConverter;

    @PostMapping("/login")
    public Result login(@RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);
        return new Result(true, StatusCode.SUCCESS, "Login Success", Map.of("token", token));
    }

    @PostMapping("/register")
    public Result register(@RequestBody RegisterDto registerDto) {
        authService.register(registerDto);
        return new Result(true, StatusCode.SUCCESS, "Register Success");
    }

    @GetMapping("/me")
    public Result getMyProfile(JwtAuthenticationToken jwtAuthenticationToken) {
        Jwt jwt = jwtAuthenticationToken.getToken();
        String userId = jwt.getId();
        MyUser myUser = userService.findById(userId);
        UserDto userDto = userToUserDtoConverter.convert(myUser);
        return new Result(true, StatusCode.SUCCESS, "Get My Profile Success", userDto);
    }
}
