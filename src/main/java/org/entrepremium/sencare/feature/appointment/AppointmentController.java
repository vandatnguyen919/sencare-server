package org.entrepremium.sencare.feature.appointment;

import lombok.RequiredArgsConstructor;
import org.entrepremium.sencare.feature.appointment.converter.AppointmentToDtoConverter;
import org.entrepremium.sencare.feature.appointment.dto.AppointmentDto;
import org.entrepremium.sencare.system.Result;
import org.entrepremium.sencare.system.StatusCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.endpoint.base-url}/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final AppointmentToDtoConverter appointmentToDtoConverter;

    @GetMapping
    public Result findAllAppointments(Pageable pageable) {
        Page<Appointment> foundAppointments = appointmentService.findAll(pageable);
        Page<AppointmentDto> appointmentDtos = foundAppointments.map(appointmentToDtoConverter::convert);
        return new Result(true, StatusCode.SUCCESS, "Find All Appointments Success", appointmentDtos);
    }

    @GetMapping("/{appointmentId}")
    public Result findAppointmentById(@PathVariable String appointmentId) {
        Appointment foundAppointment = appointmentService.findById(appointmentId);
        AppointmentDto appointmentDto = appointmentToDtoConverter.convert(foundAppointment);
        return new Result(true, StatusCode.SUCCESS, "Find One Appointment Success", appointmentDto);
    }

    @GetMapping("/status/{status}")
    public Result findAppointmentsByStatus(@PathVariable String status) {
        List<Appointment> foundAppointments = appointmentService.findByStatus(status);
        List<AppointmentDto> appointmentDtos = foundAppointments.stream()
                .map(appointmentToDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find Appointments By Status Success", appointmentDtos);
    }

    @GetMapping("/doctor/{doctorId}")
    public Result findAppointmentsByDoctorId(@PathVariable String doctorId) {
        List<Appointment> foundAppointments = appointmentService.findByDoctorId(doctorId);
        List<AppointmentDto> appointmentDtos = foundAppointments.stream()
                .map(appointmentToDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find Appointments By Doctor Success", appointmentDtos);
    }

    @GetMapping("/user/{userId}")
    public Result findAppointmentsByUserId(@PathVariable String userId) {
        List<Appointment> foundAppointments = appointmentService.findByUserId(userId);
        List<AppointmentDto> appointmentDtos = foundAppointments.stream()
                .map(appointmentToDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find Appointments By User Success", appointmentDtos);
    }

    @PostMapping
    public Result addAppointment(@RequestBody Appointment newAppointment) {
        Appointment savedAppointment = appointmentService.save(newAppointment);
        AppointmentDto savedAppointmentDto = appointmentToDtoConverter.convert(savedAppointment);
        return new Result(true, StatusCode.SUCCESS, "Add Appointment Success", savedAppointmentDto);
    }

    @PutMapping("/{appointmentId}")
    public Result updateAppointment(@PathVariable String appointmentId, @RequestBody Appointment update) {
        Appointment updatedAppointment = appointmentService.update(appointmentId, update);
        AppointmentDto updatedAppointmentDto = appointmentToDtoConverter.convert(updatedAppointment);
        return new Result(true, StatusCode.SUCCESS, "Update Appointment Success", updatedAppointmentDto);
    }

    @DeleteMapping("/{appointmentId}")
    public Result deleteAppointment(@PathVariable String appointmentId) {
        appointmentService.delete(appointmentId);
        return new Result(true, StatusCode.SUCCESS, "Delete Appointment Success");
    }
}
