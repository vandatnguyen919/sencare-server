package org.entrepremium.sencare.features.review.converter;

import org.entrepremium.sencare.features.review.Review;
import org.entrepremium.sencare.features.review.dto.ReviewDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ReviewToReviewDtoConverter implements Converter<Review, ReviewDto> {

    @Override
    public ReviewDto convert(@NonNull Review source) {
        return new ReviewDto(
                source.getId(),
                source.getRating(),
                source.getContent(),
                source.getCreatedAt(),
                source.getUpdatedAt()
        );
    }
}
