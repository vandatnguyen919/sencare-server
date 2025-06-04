package org.entrepremium.sencare.feature.hospital.dto;

import org.entrepremium.sencare.feature.doctor.dto.DoctorDto;
import org.entrepremium.sencare.feature.hospitalspec.dto.HospitalSpecDto;

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