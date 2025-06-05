package org.entrepremium.sencare.feature.workexperience.converter;

import org.entrepremium.sencare.feature.workexperience.WorkExperience;
import org.entrepremium.sencare.feature.workexperience.dto.WorkExperienceDto;
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
