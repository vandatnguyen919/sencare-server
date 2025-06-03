package org.entrepremium.sencare.features.hospitalsystem.specialization.dto;

import org.entrepremium.sencare.features.hospitalsystem.hospitalspec.dto.HospitalSpecDto;

import java.util.List;

public record SpecializationDto(
        String specId,
        String specName,
        String specDescription,
        List<HospitalSpecDto> hospitalSpecs
) {}