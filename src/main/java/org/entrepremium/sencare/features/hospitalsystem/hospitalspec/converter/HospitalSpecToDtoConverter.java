package org.entrepremium.sencare.features.hospitalsystem.hospitalspec.converter;

import org.entrepremium.sencare.features.hospitalsystem.hospitalspec.HospitalSpec;
import org.entrepremium.sencare.features.hospitalsystem.hospitalspec.dto.HospitalSpecDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class HospitalSpecToDtoConverter implements Converter<HospitalSpec, HospitalSpecDto> {

    @Override
    public HospitalSpecDto convert(HospitalSpec source) {
        return new HospitalSpecDto(
                source.getId(),
                source.getHospital() != null ? source.getHospital().getHospitalId() : null,
                source.getHospital() != null ? source.getHospital().getHospitalName() : null,
                source.getSpecialization() != null ? source.getSpecialization().getSpecId() : null,
                source.getSpecialization() != null ? source.getSpecialization().getSpecName() : null
        );
    }
}
