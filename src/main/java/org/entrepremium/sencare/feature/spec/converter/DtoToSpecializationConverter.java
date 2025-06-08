package org.entrepremium.sencare.feature.spec.converter;

import org.entrepremium.sencare.feature.spec.Specialization;
import org.entrepremium.sencare.feature.spec.dto.SpecializationDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

public class DtoToSpecializationConverter implements Converter<SpecializationDto, Specialization> {

    @Override
    public Specialization convert(@NonNull SpecializationDto source) {
        Specialization spec = new Specialization();
        spec.setSpecName(source.specName());
        spec.setSpecDescription(source.specDescription());
        spec.setSpecId(source.specId());
        return null;
    }
}

