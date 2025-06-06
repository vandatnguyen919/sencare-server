package org.entrepremium.sencare.feature.education.converter;

import org.entrepremium.sencare.feature.education.Education;
import org.entrepremium.sencare.feature.education.dto.EducationDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EducationToDtoConverter implements Converter<Education, EducationDto> {
    @Override
    public EducationDto convert(Education source) {
        return new EducationDto(
                source.getEduId(),
                source.getCollegeName(),
                source.getDescription(),
                source.getIssuedBy(),
                source.getIssuedYear(),
                source.getDoctor() != null ? source.getDoctor().getDoctorId() : null,
                source.getDoctor() != null ? source.getDoctor().getDoctorName() : null
        );
    }
}
