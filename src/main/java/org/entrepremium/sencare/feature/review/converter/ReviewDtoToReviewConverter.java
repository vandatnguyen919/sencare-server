package org.entrepremium.sencare.feature.review.converter;

import org.entrepremium.sencare.feature.review.Review;
import org.entrepremium.sencare.feature.review.dto.ReviewDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ReviewDtoToReviewConverter implements Converter<ReviewDto, Review> {

    @Override
    public Review convert(@NonNull ReviewDto source) {
        Review review = new Review();
        review.setRating(source.rating());
        review.setContent(source.content());
        return review;
    }
}
