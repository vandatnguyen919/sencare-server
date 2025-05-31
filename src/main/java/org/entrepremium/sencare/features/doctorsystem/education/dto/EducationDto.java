package org.entrepremium.sencare.features.doctorsystem.education.dto;

public record EducationDto(
        String eduId,
        String collegeName,
        String description,
        String issuedBy,
        Integer issuedYear,
        String doctorId,
        String doctorName
) {}