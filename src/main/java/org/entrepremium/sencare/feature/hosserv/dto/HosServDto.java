package org.entrepremium.sencare.feature.hosserv.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record HosServDto(
        String id,
        String servName,
        String servDesc,
        double servPrice,
        String servImage,
        boolean available,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime createdAt,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime updatedAt,
        String hospitalId
) {
}