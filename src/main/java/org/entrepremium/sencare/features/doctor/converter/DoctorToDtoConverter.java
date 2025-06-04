package org.entrepremium.sencare.features.doctor.converter;

import org.entrepremium.sencare.features.doctor.Doctor;
import org.entrepremium.sencare.features.doctor.dto.DoctorDto;
import org.entrepremium.sencare.features.education.converter.EducationToDtoConverter;
import org.entrepremium.sencare.features.workexperience.converter.WorkExperienceToDtoConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DoctorToDtoConverter implements Converter<Doctor, DoctorDto> {

    private final EducationToDtoConverter educationConverter;
    private final WorkExperienceToDtoConverter workExperienceConverter;

    public DoctorToDtoConverter(EducationToDtoConverter educationConverter,
                                WorkExperienceToDtoConverter workExperienceConverter) {
        this.educationConverter = educationConverter;
        this.workExperienceConverter = workExperienceConverter;
    }

    @Override
    public DoctorDto convert(Doctor source) {
        return new DoctorDto(
                source.getDoctorId(),
                source.getDoctorAvatar(),
                source.getDoctorName(),
                source.getDoctorDescription(),
                source.getDoctorPrice(),
                source.getHospital() != null ? source.getHospital().getHospitalId() : null,
                source.getHospital() != null ? source.getHospital().getHospitalName() : null,
                source.getEducations().stream()
                        .map(educationConverter::convert)
                        .collect(Collectors.toList()),
                source.getWorkExperiences().stream()
                        .map(workExperienceConverter::convert)
                        .collect(Collectors.toList())
        );
    }
}