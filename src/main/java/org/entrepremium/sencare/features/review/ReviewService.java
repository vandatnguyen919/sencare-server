package org.entrepremium.sencare.features.review;

import jakarta.transaction.Transactional;
import org.entrepremium.sencare.system.exception.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Page<Review> findAll(Pageable pageable) {
        return reviewRepository.findAll(pageable);
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