package org.entrepremium.sencare.feature.education.converter;

import org.entrepremium.sencare.feature.education.Education;
import org.entrepremium.sencare.feature.education.dto.EducationDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

public class DtoToEducationConverter implements Converter<EducationDto, Education> {

    @Override
    public Education convert(@NonNull EducationDto source) {
        Education edu = new Education();
        edu.setEduId(source.eduId());
        edu.setDescription(source.description());
        edu.setIssuedBy(String.valueOf(source.issuedBy()));
        edu.setCollegeName(source.collegeName());
        edu.setIssuedYear(source.issuedYear());
        return null;
    }
}