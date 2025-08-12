package org.entrepremium.sencare.feature.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardSummaryDto {
    private String unit;
    private LocalDateTime start;
    private LocalDateTime end;
    private long totalOrders;
    private double revenue;
    private Map<String, Long> statusCounts; // PENDING, CONFIRMED, COMPLETED, CANCELLED
    private long totalServicesBooked;
}
