package org.entrepremium.sencare.features.review.converter;

import org.entrepremium.sencare.features.review.Review;
import org.entrepremium.sencare.features.review.dto.ReviewDto;
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
        return null;
    }
}
