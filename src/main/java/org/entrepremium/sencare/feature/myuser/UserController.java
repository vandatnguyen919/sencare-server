package org.entrepremium.sencare.feature.myuser;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.entrepremium.sencare.feature.myuser.converter.UserDtoToUserConverter;
import org.entrepremium.sencare.feature.myuser.converter.UserToUserDtoConverter;
import org.entrepremium.sencare.feature.myuser.dto.UserDto;
import org.entrepremium.sencare.system.Result;
import org.entrepremium.sencare.system.StatusCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.endpoint.base-url}/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserToUserDtoConverter userToUserDtoConverter;

    private final UserDtoToUserConverter userDtoToUserConverter;

    // Using Pageable directly in the controller method signature,
    // let Spring handles the instantiation and population of PageRequest object based on the request parameters.
    // It is a common and recommended approach when implement pagination in Spring Boot
    @GetMapping
    public Result getAllUsers(Pageable pageable) {
        Page<MyUser> myUserPage = userService.findAll(pageable);
        Page<UserDto> userDtoPage = myUserPage.map(userToUserDtoConverter::convert);
        return new Result(true, StatusCode.SUCCESS, "Find All Success", userDtoPage);
    }

    @GetMapping("/{userId}")
    public Result getUserById(@PathVariable String userId) {
        MyUser myUser = userService.findById(userId);
        UserDto userDto = userToUserDtoConverter.convert(myUser);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", userDto);
    }

    @PostMapping
    public Result addUser(@Valid @RequestBody MyUser newUser) {
        MyUser savedUser = userService.save(newUser);
        UserDto savedUserDto = userToUserDtoConverter.convert(savedUser);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedUserDto);
    }

    @PutMapping("/{userId}")
    public Result updateUser(@PathVariable String userId, @Valid @RequestBody UserDto userDto) {
        MyUser update = userDtoToUserConverter.convert(userDto);
        MyUser updatedUser = userService.update(userId, update);
        UserDto updatedUserDto = userToUserDtoConverter.convert(updatedUser);
        return new Result(true, StatusCode.SUCCESS, "Update Success", updatedUserDto);
    }

    @DeleteMapping("/{userId}")
    public Result deleteUser(@PathVariable String userId) {
        this.userService.delete(userId);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");
    }
}
