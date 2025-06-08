package org.entrepremium.sencare.feature.hosserv.converter;

import org.entrepremium.sencare.feature.hosserv.HosServ;
import org.entrepremium.sencare.feature.hosserv.dto.HosServDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class HosServToHosServDtoConverter implements Converter<HosServ, HosServDto> {

    @Override
    public HosServDto convert(HosServ source) {
        return new HosServDto(
                source.getId(),
                source.getServName(),
                source.getServDesc(),
                BigDecimal.valueOf(source.getServPrice()),
                source.getServImage(),
                source.isAvailable(),
                source.getCreatedAt(),
                source.getUpdatedAt(),
                source.getHospital() != null ? source.getHospital().getHospitalId() : null
        );
    }
}
