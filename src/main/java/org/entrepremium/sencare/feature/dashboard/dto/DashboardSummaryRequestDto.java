package org.entrepremium.sencare.feature.dashboard.dto;

import lombok.Data;

@Data
public class DashboardSummaryRequestDto {
    private String unit; // DAY | MONTH | YEAR | CUSTOM
    private String date; // yyyy-MM-dd (for DAY)
    private String month; // yyyy-MM (for MONTH)
    private String year; // yyyy (for YEAR)
    private String fromDate; // yyyy-MM-dd (for CUSTOM)
    private String toDate; // yyyy-MM-dd (for CUSTOM)
}
