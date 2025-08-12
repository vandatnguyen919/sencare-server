package org.entrepremium.sencare.feature.dashboard;

import lombok.RequiredArgsConstructor;
import org.entrepremium.sencare.feature.dashboard.dto.DashboardSummaryDto;
import org.entrepremium.sencare.feature.dashboard.dto.DashboardSummaryRequestDto;
import org.entrepremium.sencare.system.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.endpoint.base-url}/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    @PostMapping("/summary")
    public ResponseEntity<Result> getSummary(@RequestBody DashboardSummaryRequestDto request) {
        DashboardSummaryDto summary = dashboardService.getSummary(request);
        Result result = new Result(true, HttpStatus.OK.value(), "Dashboard summary", summary);
        return ResponseEntity.ok(result);
    }
}
