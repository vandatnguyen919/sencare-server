package org.entrepremium.sencare.features.hospitalsystem.hospital.dto;

import org.entrepremium.sencare.features.doctorsystem.doctor.dto.DoctorDto;
import org.entrepremium.sencare.features.hospitalsystem.hospitalspec.dto.HospitalSpecDto;

import java.util.List;

public record HospitalDto(
        String hospitalId,
        String hospitalAvatar,
        String hospitalName,
        String hospitalDescription,
        String hospitalPhone,
        String userId,
        List<DoctorDto> doctors,
        List<HospitalSpecDto> hospitalSpecs
) {}