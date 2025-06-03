package org.entrepremium.sencare.feature.doctorsystem.workexperience.dto;

import java.time.LocalDate;

public record WorkExperienceDto(
        String wexId,
        String hospitalName,
        String jobTitle,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        String doctorId,
        String doctorName
) {}