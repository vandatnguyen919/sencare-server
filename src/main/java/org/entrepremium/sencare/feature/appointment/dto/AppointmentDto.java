package org.entrepremium.sencare.feature.appointment.dto;

import org.entrepremium.sencare.feature.timeslot.dto.TimeslotDto;
import java.time.LocalDateTime;
import java.util.List;

public record AppointmentDto(
        String id,
        String description,
        String status,
        LocalDateTime createdAt,
        String doctorId,
        String doctorName,
        String userId,
        String userEmail,
        List<TimeslotDto> timeslots
) {}
