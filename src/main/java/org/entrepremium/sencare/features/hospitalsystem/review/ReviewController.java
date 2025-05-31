package org.entrepremium.sencare.features.hospitalsystem.review;

import org.entrepremium.sencare.system.Result;
import org.entrepremium.sencare.system.StatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.endpoint.base-url}/reviews")
public class ReviewController {

    @GetMapping
    public Result findAllReviews() {
        return new Result(true, StatusCode.SUCCESS, "Success");
    }
}
