package org.entrepremium.sencare.feature.hospital.converter;

import org.entrepremium.sencare.feature.hospital.Hospital;
import org.entrepremium.sencare.feature.hospital.dto.HospitalDto;
import org.entrepremium.sencare.feature.doctor.converter.DoctorToDtoConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class HospitalToDtoConverter implements Converter<Hospital, HospitalDto> {

    private final DoctorToDtoConverter doctorConverter;

    public HospitalToDtoConverter(DoctorToDtoConverter doctorConverter) {
        this.doctorConverter = doctorConverter;
    }

    @Override
    public HospitalDto convert(Hospital source) {
        return new HospitalDto(
                source.getHospitalId(),
                source.getHospitalAvatar(),
                source.getHospitalName(),
                source.getHospitalDescription(),
                source.getHospitalPhone(),
                source.getUser() != null ? source.getUser().getId() : null,
                source.getDoctors().stream()
                        .map(doctorConverter::convert)
                        .collect(Collectors.toList())
        );
    }
}
