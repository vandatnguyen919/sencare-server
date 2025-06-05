package org.entrepremium.sencare.feature.review;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.entrepremium.sencare.feature.review.converter.ReviewDtoToReviewConverter;
import org.entrepremium.sencare.feature.review.converter.ReviewToReviewDtoConverter;
import org.entrepremium.sencare.feature.review.dto.ReviewDto;
import org.entrepremium.sencare.system.Result;
import org.entrepremium.sencare.system.StatusCode;
import org.entrepremium.sencare.system.utils.JwtUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.endpoint.base-url}/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewToReviewDtoConverter reviewToReviewDtoConverter;
    private final ReviewDtoToReviewConverter reviewDtoToReviewConverter;

    @GetMapping
    public Result getAllReviews(Pageable pageable) {
        Page<Review> reviewPage = reviewService.findAll(pageable);
        Page<ReviewDto> reviewDtoPage = reviewPage.map(reviewToReviewDtoConverter::convert);
        return new Result(true, StatusCode.SUCCESS, "Find All Success", reviewDtoPage);
    }

    @GetMapping("/hospitals/{hospitalId}")
    public Result getReviewsByHospitalId(Pageable pageable, @PathVariable String hospitalId) {
        Page<Review> reviewPage = reviewService.findByHospitalId(pageable, hospitalId);
        Page<ReviewDto> reviewDtoPage = reviewPage.map(reviewToReviewDtoConverter::convert);
        return new Result(true, StatusCode.SUCCESS, "Find All Success", reviewDtoPage);
    }

    @GetMapping("/doctors/{doctorId}")
    public Result getReviewsByDoctorId(Pageable pageable, @PathVariable String doctorId) {
        Page<Review> reviewPage = reviewService.findByDoctorId(pageable, doctorId);
        Page<ReviewDto> reviewDtoPage = reviewPage.map(reviewToReviewDtoConverter::convert);
        return new Result(true, StatusCode.SUCCESS, "Find All Success", reviewDtoPage);
    }

    @GetMapping("/hosservs/{hosservId}")
    public Result getReviewsByHosServId(Pageable pageable, @PathVariable String hosservId) {
        Page<Review> reviewPage = reviewService.findByHosServId(pageable, hosservId);
        Page<ReviewDto> reviewDtoPage = reviewPage.map(reviewToReviewDtoConverter::convert);
        return new Result(true, StatusCode.SUCCESS, "Find All Success", reviewDtoPage);
    }

    @GetMapping("/{reviewId}")
    public Result getReviewById(@PathVariable String reviewId) {
        Review review = reviewService.findById(reviewId);
        ReviewDto reviewDto = reviewToReviewDtoConverter.convert(review);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", reviewDto);
    }

    @PostMapping
    public Result addReview(@Valid @RequestBody Review newReview) {
        Review savedReview = reviewService.save(newReview);
        ReviewDto savedReviewDto = reviewToReviewDtoConverter.convert(savedReview);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedReviewDto);
    }

    @PostMapping("/doctors/{doctorId}")
    public Result createDoctorReview(@PathVariable String doctorId, @Valid @RequestBody Review newReview, JwtAuthenticationToken jwtAuthenticationToken) {
        String userId = JwtUtils.getUserId(jwtAuthenticationToken);
        Review savedReview = reviewService.createDoctorReview(userId, doctorId, newReview);
        ReviewDto savedReviewDto = reviewToReviewDtoConverter.convert(savedReview);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedReviewDto);
    }

    @PostMapping("/hosserv/{hosservId}")
    public Result createHosServReview(@PathVariable String hosservId, @Valid @RequestBody Review newReview, JwtAuthenticationToken jwtAuthenticationToken) {
        String userId = JwtUtils.getUserId(jwtAuthenticationToken);
        Review savedReview = reviewService.createHosServReview(userId, hosservId, newReview);
        ReviewDto savedReviewDto = reviewToReviewDtoConverter.convert(savedReview);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedReviewDto);
    }

    @PutMapping("/{reviewId}")
    public Result updateReview(@PathVariable String reviewId, @Valid @RequestBody ReviewDto reviewDto) {
        Review update = reviewDtoToReviewConverter.convert(reviewDto);
        Review updatedReview = reviewService.update(reviewId, update);
        ReviewDto updatedReviewDto = reviewToReviewDtoConverter.convert(updatedReview);
        return new Result(true, StatusCode.SUCCESS, "Update Success", updatedReviewDto);
    }

    @DeleteMapping("/{reviewId}")
    public Result deleteReview(@PathVariable String reviewId) {
        this.reviewService.delete(reviewId);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");
    }
}