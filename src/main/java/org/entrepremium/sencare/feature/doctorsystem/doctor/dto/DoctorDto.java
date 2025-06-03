package org.entrepremium.sencare.feature.doctorsystem.doctor.dto;

import org.entrepremium.sencare.feature.doctorsystem.education.dto.EducationDto;
import org.entrepremium.sencare.feature.doctorsystem.workexperience.dto.WorkExperienceDto;

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