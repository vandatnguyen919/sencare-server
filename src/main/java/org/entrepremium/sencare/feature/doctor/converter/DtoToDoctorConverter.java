package org.entrepremium.sencare.feature.doctor.converter;

import org.entrepremium.sencare.feature.doctor.Doctor;
import org.entrepremium.sencare.feature.doctor.dto.DoctorDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

public class DtoToDoctorConverter implements Converter<DoctorDto, Doctor> {

    @Override
    public Doctor convert(@NonNull DoctorDto source) {
        Doctor doctor = new Doctor();
        doctor.setDoctorId(source.doctorId());
        doctor.setDoctorName(source.doctorName());
        doctor.setDoctorPrice(source.doctorPrice());
        doctor.setDoctorAvatar(source.doctorAvatar());
        doctor.setDoctorDescription(source.doctorDescription());
        return null;
    }
}