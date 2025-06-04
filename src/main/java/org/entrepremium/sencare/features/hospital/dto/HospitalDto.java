package org.entrepremium.sencare.features.hospital.dto;

import org.entrepremium.sencare.features.doctor.dto.DoctorDto;
import org.entrepremium.sencare.features.hospitalspec.dto.HospitalSpecDto;

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