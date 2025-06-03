package org.entrepremium.sencare.features.hospitalsystem.hospital.converter;

import org.entrepremium.sencare.features.hospitalsystem.hospital.Hospital;
import org.entrepremium.sencare.features.hospitalsystem.hospital.dto.HospitalDto;
import org.entrepremium.sencare.features.doctorsystem.doctor.converter.DoctorToDtoConverter;
import org.entrepremium.sencare.features.hospitalsystem.hospitalspec.converter.HospitalSpecToDtoConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class HospitalToDtoConverter implements Converter<Hospital, HospitalDto> {

    private final DoctorToDtoConverter doctorConverter;
    private final HospitalSpecToDtoConverter hospitalSpecConverter;

    public HospitalToDtoConverter(DoctorToDtoConverter doctorConverter,
                                  HospitalSpecToDtoConverter hospitalSpecConverter) {
        this.doctorConverter = doctorConverter;
        this.hospitalSpecConverter = hospitalSpecConverter;
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
                        .collect(Collectors.toList()),
                source.getHospitalSpecs().stream()
                        .map(hospitalSpecConverter::convert)
                        .collect(Collectors.toList())
        );
    }
}
