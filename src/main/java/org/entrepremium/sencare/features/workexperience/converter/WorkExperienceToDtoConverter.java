package org.entrepremium.sencare.features.workexperience.converter;

import org.entrepremium.sencare.features.workexperience.WorkExperience;
import org.entrepremium.sencare.features.workexperience.dto.WorkExperienceDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WorkExperienceToDtoConverter implements Converter<WorkExperience, WorkExperienceDto> {
    @Override
    public WorkExperienceDto convert(WorkExperience source) {
        return new WorkExperienceDto(
                source.getWexId(),
                source.getHospitalName(),
                source.getJobTitle(),
                source.getDescription(),
                source.getStartDate(),
                source.getEndDate(),
                source.getDoctor() != null ? source.getDoctor().getDoctorId() : null,
                source.getDoctor() != null ? source.getDoctor().getDoctorName() : null
        );
    }
}