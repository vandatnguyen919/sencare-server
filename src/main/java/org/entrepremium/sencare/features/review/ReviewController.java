package org.entrepremium.sencare.features.review;

import jakarta.validation.Valid;
import org.entrepremium.sencare.features.review.converter.ReviewDtoToReviewConverter;
import org.entrepremium.sencare.features.review.converter.ReviewToReviewDtoConverter;
import org.entrepremium.sencare.features.review.dto.ReviewDto;
import org.entrepremium.sencare.system.Result;
import org.entrepremium.sencare.system.StatusCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.endpoint.base-url}/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewToReviewDtoConverter reviewToReviewDtoConverter;
    private final ReviewDtoToReviewConverter reviewDtoToReviewConverter;

    public ReviewController(ReviewService reviewService,
                            ReviewToReviewDtoConverter reviewToReviewDtoConverter,
                            ReviewDtoToReviewConverter reviewDtoToReviewConverter) {
        this.reviewService = reviewService;
        this.reviewToReviewDtoConverter = reviewToReviewDtoConverter;
        this.reviewDtoToReviewConverter = reviewDtoToReviewConverter;
    }

    @GetMapping
    public Result getAllReviews(Pageable pageable) {
        Page<Review> reviewPage = reviewService.findAll(pageable);
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

//    @GetMapping("/user/{userId}")
//    public Result getReviewsByUserId(@PathVariable String userId) {
//        List<Review> reviews = reviewService.findByUserId(userId);
//        List<ReviewDto> reviewDtos = reviews.stream()
//                .map(reviewToReviewDtoConverter::convert)
//                .toList();
//        return new Result(true, StatusCode.SUCCESS, "Find By User Success", reviewDtos);
//    }
//
//    @GetMapping("/service/{serviceId}")
//    public Result getReviewsByServiceId(@PathVariable String serviceId) {
//        List<Review> reviews = reviewService.findByServiceId(serviceId);
//        List<ReviewDto> reviewDtos = reviews.stream()
//                .map(reviewToReviewDtoConverter::convert)
//                .toList();
//        return new Result(true, StatusCode.SUCCESS, "Find By Service Success", reviewDtos);
//    }
}