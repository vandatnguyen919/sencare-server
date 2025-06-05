package org.entrepremium.sencare.feature.review.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ReviewDto(
    String id,
    double rating,
    String content,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdAt,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime updatedAt,
    String hospitalId,
    String hosServId,
    String doctorId,
    String createdById
) {
}
