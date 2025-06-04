package org.entrepremium.sencare.features.specialization.converter;

import org.entrepremium.sencare.features.specialization.Specialization;
import org.entrepremium.sencare.features.specialization.dto.SpecializationDto;
import org.entrepremium.sencare.features.hospitalspec.converter.HospitalSpecToDtoConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SpecializationToDtoConverter implements Converter<Specialization, SpecializationDto> {

    private final HospitalSpecToDtoConverter hospitalSpecConverter;

    public SpecializationToDtoConverter(HospitalSpecToDtoConverter hospitalSpecConverter) {
        this.hospitalSpecConverter = hospitalSpecConverter;
    }

    @Override
    public SpecializationDto convert(Specialization source) {
        return new SpecializationDto(
                source.getSpecId(),
                source.getSpecName(),
                source.getSpecDescription(),
                source.getHospitalSpecs().stream()
                        .map(hospitalSpecConverter::convert)
                        .collect(Collectors.toList())
        );
    }
}
