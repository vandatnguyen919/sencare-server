package org.entrepremium.sencare.feature.timeslot.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record TimeslotDto(
        String id,
        LocalTime startTime,
        LocalTime endTime,
        LocalDate date,
        boolean isOccupied,
        String doctorId,
        String doctorName,
        String hosServId,
        String hosServName
) {}
