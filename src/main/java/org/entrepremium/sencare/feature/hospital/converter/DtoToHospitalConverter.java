package org.entrepremium.sencare.feature.hospital.converter;

import org.entrepremium.sencare.feature.hospital.Hospital;
import org.entrepremium.sencare.feature.hospital.dto.HospitalDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

public class DtoToHospitalConverter implements Converter<HospitalDto, Hospital> {

    @Override
    public Hospital convert(@NonNull HospitalDto source) {
        Hospital hospital = new Hospital();
        hospital.setHospitalId(source.hospitalId());
        hospital.setHospitalAvatar(source.hospitalAvatar());
        hospital.setHospitalName(source.hospitalName());
        hospital.setHospitalDescription(source.hospitalDescription());
        hospital.setHospitalPhone(source.hospitalPhone());
        return null;
    }
}
