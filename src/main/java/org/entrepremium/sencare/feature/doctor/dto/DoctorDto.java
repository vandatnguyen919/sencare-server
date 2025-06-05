package org.entrepremium.sencare.feature.doctor.dto;

import org.entrepremium.sencare.feature.education.dto.EducationDto;
import org.entrepremium.sencare.feature.workexperience.dto.WorkExperienceDto;

import java.math.BigDecimal;
import java.util.List;

public record DoctorDto(
        String doctorId,
        String doctorAvatar,
        String doctorName,
        String doctorDescription,
        BigDecimal doctorPrice,
        String hospitalId,
        String hospitalName,
        List<EducationDto> educations,
        List<WorkExperienceDto> workExperiences
) {}