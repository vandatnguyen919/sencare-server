package org.entrepremium.sencare.feature.timeslot.converter;

import org.entrepremium.sencare.feature.timeslot.Timeslot;
import org.entrepremium.sencare.feature.timeslot.dto.TimeslotDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TimeslotToDtoConverter implements Converter<Timeslot, TimeslotDto> {

    @Override
    public TimeslotDto convert(Timeslot source) {
        return new TimeslotDto(
                source.getId(),
                source.getStartTime(),
                source.getEndTime(),
                source.getDate(),
                source.isOccupied(),
                source.getDoctor() != null ? source.getDoctor().getDoctorId() : null,
                source.getDoctor() != null ? source.getDoctor().getDoctorName() : null,
                source.getHosServ() != null ? source.getHosServ().getId() : null,
                source.getHosServ() != null ? source.getHosServ().getServName() : null
        );
    }
}
