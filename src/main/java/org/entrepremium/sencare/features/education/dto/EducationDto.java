package org.entrepremium.sencare.features.education.dto;

public record EducationDto(
        String eduId,
        String collegeName,
        String description,
        String issuedBy,
        Integer issuedYear,
        String doctorId,
        String doctorName
) {}