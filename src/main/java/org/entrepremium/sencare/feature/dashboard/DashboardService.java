package org.entrepremium.sencare.feature.dashboard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.entrepremium.sencare.feature.dashboard.dto.DashboardSummaryDto;
import org.entrepremium.sencare.feature.dashboard.dto.DashboardSummaryRequestDto;
import org.entrepremium.sencare.feature.order.Order;
import org.entrepremium.sencare.feature.order.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DashboardService {

    private final OrderRepository orderRepository;

    public DashboardSummaryDto getSummary(DashboardSummaryRequestDto request) {
        LocalDateTime start;
        LocalDateTime end;

        String unit = request.getUnit().toUpperCase();

        switch (unit) {
            case "CUSTOM":
                if (request.getFromDate() == null || request.getToDate() == null) {
                    throw new IllegalArgumentException("fromDate and toDate are required for CUSTOM unit");
                }
                LocalDate from = LocalDate.parse(request.getFromDate());
                LocalDate to = LocalDate.parse(request.getToDate());
                start = from.atStartOfDay();
                end = to.atTime(LocalTime.MAX);
                break;

            case "DAY":
                LocalDate day = LocalDate.parse(request.getDate());
                start = day.atStartOfDay();
                end = day.atTime(LocalTime.MAX);
                break;

            case "MONTH":
                YearMonth ym = YearMonth.parse(request.getMonth());
                start = ym.atDay(1).atStartOfDay();
                end = ym.atEndOfMonth().atTime(LocalTime.MAX);
                break;

            case "YEAR":
                Year year = Year.parse(request.getYear());
                start = year.atDay(1).atStartOfDay();
                end = year.atMonth(Month.DECEMBER).atEndOfMonth().atTime(LocalTime.MAX);
                break;

            default:
                throw new IllegalArgumentException("Invalid unit: " + unit);
        }

        log.info("Fetching dashboard summary from {} to {}", start, end);

        long totalOrders = orderRepository.countByCreatedAtBetween(start, end);
        double revenue = orderRepository.sumRevenueByStatusAndCreatedAtBetween(
                Order.OrderStatus.COMPLETED, start, end
        );
        Map<String, Long> statusCounts = orderRepository.countGroupByStatusBetween(start, end)
                .stream()
                .collect(Collectors.toMap(
                        obj -> ((Order.OrderStatus) obj[0]).name(),
                        obj -> (Long) obj[1]
                ));

        // NEW: total services booked
        long totalServicesBooked = orderRepository.countServicesBookedBetween(start, end);

        return DashboardSummaryDto.builder()
                .unit(unit)
                .start(start)
                .end(end)
                .totalOrders(totalOrders)
                .revenue(revenue != 0 ? revenue : 0.0)
                .statusCounts(statusCounts)
                .totalServicesBooked(totalServicesBooked) // NEW FIELD
                .build();

    }
}