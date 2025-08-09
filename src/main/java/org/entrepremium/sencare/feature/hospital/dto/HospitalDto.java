package org.entrepremium.sencare.feature.hospital.dto;

import org.entrepremium.sencare.feature.doctor.dto.DoctorDto;
import org.entrepremium.sencare.feature.hosserv.dto.HosServDto;
import org.entrepremium.sencare.feature.spec.dto.SpecializationDto;

import java.util.List;

public record HospitalDto(
        String hospitalId,
        String hospitalAvatar,
        String hospitalName,
        String hospitalDescription,
        String hospitalPhone,
        String userId,
        List<DoctorDto> doctors,
        List<HosServDto> hosServs,
        List<String> specializations,
        double rating
) {}