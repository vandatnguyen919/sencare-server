package org.entrepremium.sencare.feature.hosserv.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.entrepremium.sencare.system.util.VndCurrencySerializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record HosServDto(
        String id,
        String servName,
        String servDesc,
        BigDecimal servPrice,
        String servImage,
        boolean available,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime createdAt,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime updatedAt,
        String hospitalId
) {
}