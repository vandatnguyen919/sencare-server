package org.entrepremium.sencare.feature.spec.converter;

import org.entrepremium.sencare.feature.spec.Specialization;
import org.entrepremium.sencare.feature.spec.dto.SpecializationDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SpecializationToDtoConverter implements Converter<Specialization, SpecializationDto> {

    @Override
    public SpecializationDto convert(Specialization source) {
        return new SpecializationDto(
                source.getSpecId(),
                source.getSpecName(),
                source.getSpecDescription()
        );
    }
}
