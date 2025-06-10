package org.entrepremium.sencare.feature.specialization.converter;

import org.entrepremium.sencare.feature.specialization.Specialization;
import org.entrepremium.sencare.feature.specialization.dto.SpecializationDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SpecializationToDtoConverter implements Converter<Specialization, SpecializationDto> {


    public SpecializationToDtoConverter() {
    }

    @Override
    public SpecializationDto convert(Specialization source) {
        return new SpecializationDto(
                source.getSpecId(),
                source.getSpecName(),
                source.getSpecDescription()
        );
    }
}
