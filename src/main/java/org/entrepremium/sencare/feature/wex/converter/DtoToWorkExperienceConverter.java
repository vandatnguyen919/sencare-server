package org.entrepremium.sencare.feature.wex.converter;

import org.entrepremium.sencare.feature.wex.WorkExperience;
import org.entrepremium.sencare.feature.wex.dto.WorkExperienceDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

public class DtoToWorkExperienceConverter implements Converter<WorkExperienceDto, WorkExperience> {

    @Override
    public WorkExperience convert(@NonNull WorkExperienceDto source) {
        WorkExperience wex = new WorkExperience();
        wex.setDescription(source.description());
        wex.setWexId(source.wexId());
        wex.setEndDate(source.endDate());
        wex.setStartDate(source.startDate());
        wex.setJobTitle(source.jobTitle());
        wex.setHospitalName(source.hospitalName());
        return null;
    }
}
