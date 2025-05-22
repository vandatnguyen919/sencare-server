package org.entrepremium.sencare.features.myuser;

import org.entrepremium.sencare.system.Result;
import org.entrepremium.sencare.system.StatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.endpoint.base-url}/users")
public class UserController {

    @GetMapping
    public Result findAllUsers() {
        return new Result(true, StatusCode.SUCCESS, "Success");
    }
}
