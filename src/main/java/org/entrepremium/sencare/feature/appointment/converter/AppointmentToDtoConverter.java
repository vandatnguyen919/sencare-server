package org.entrepremium.sencare.feature.appointment.converter;

import org.entrepremium.sencare.feature.appointment.Appointment;
import org.entrepremium.sencare.feature.appointment.dto.AppointmentDto;
import org.entrepremium.sencare.feature.timeslot.converter.TimeslotToDtoConverter;
import org.entrepremium.sencare.feature.timeslot.dto.TimeslotDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppointmentToDtoConverter implements Converter<Appointment, AppointmentDto> {

    private final TimeslotToDtoConverter timeslotConverter;

    public AppointmentToDtoConverter(TimeslotToDtoConverter timeslotConverter) {
        this.timeslotConverter = timeslotConverter;
    }

    @Override
    public AppointmentDto convert(Appointment source) {
        List<TimeslotDto> timeslotDtos = source.getTimeslots() != null
                ? source.getTimeslots().stream()
                .map(timeslotConverter::convert)
                .collect(Collectors.toList())
                : List.of();

        return new AppointmentDto(
                source.getId(),
                source.getDescription(),
                source.getStatus(),
                source.getCreatedAt(),
                source.getDoctor() != null ? source.getDoctor().getDoctorId() : null,
                source.getDoctor() != null ? source.getDoctor().getDoctorName() : null,
                source.getUser() != null ? source.getUser().getId() : null,
                source.getUser() != null ? source.getUser().getEmail() : null,
                timeslotDtos
        );
    }
}
