package org.entrepremium.sencare.features.review;

import jakarta.transaction.Transactional;
import org.entrepremium.sencare.system.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Review findById(String reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ObjectNotFoundException("review", reviewId));
    }

    public Review save(Review newReview) {
        return reviewRepository.save(newReview);
    }

    public Review update(String reviewId, Review review) {
        return reviewRepository.findById(reviewId)
                .map(oldReview -> {
                    oldReview.setContent(review.getContent());
                    oldReview.setRating(review.getRating());
                    return reviewRepository.save(oldReview);
                })
                .orElseThrow(() -> new ObjectNotFoundException("review", reviewId));
    }

    public void delete(String reviewId) {
        this.reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ObjectNotFoundException("review", reviewId));
        this.reviewRepository.deleteById(reviewId);
    }
}