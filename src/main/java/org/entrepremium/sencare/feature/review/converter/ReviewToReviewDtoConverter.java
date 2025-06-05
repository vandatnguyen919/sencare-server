package org.entrepremium.sencare.feature.review.converter;

import org.entrepremium.sencare.feature.review.Review;
import org.entrepremium.sencare.feature.review.dto.ReviewDto;
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
                source.getUpdatedAt(),
                source.getHospital() != null ? source.getHospital().getHospitalId() : null,
                source.getHosServ() != null ? source.getHosServ().getId() : null,
                source.getDoctor() != null ? source.getDoctor().getDoctorId() : null,
                source.getCreatedBy() != null ? source.getCreatedBy().getId() : null
        );
    }
}
