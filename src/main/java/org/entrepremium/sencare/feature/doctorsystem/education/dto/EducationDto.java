package org.entrepremium.sencare.feature.doctorsystem.education.dto;

public record EducationDto(
        String eduId,
        String collegeName,
        String description,
        String issuedBy,
        Integer issuedYear,
        String doctorId,
        String doctorName
) {}