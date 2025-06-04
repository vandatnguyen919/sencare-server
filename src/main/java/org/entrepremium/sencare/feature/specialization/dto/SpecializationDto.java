package org.entrepremium.sencare.feature.specialization.dto;

import org.entrepremium.sencare.feature.hospitalspec.dto.HospitalSpecDto;

import java.util.List;

public record SpecializationDto(
        String specId,
        String specName,
        String specDescription,
        List<HospitalSpecDto> hospitalSpecs
) {}