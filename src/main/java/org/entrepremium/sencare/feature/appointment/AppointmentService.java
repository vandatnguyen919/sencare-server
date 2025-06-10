package org.entrepremium.sencare.feature.appointment;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.entrepremium.sencare.system.exception.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public Page<Appointment> findAll(Pageable pageable) {
        return appointmentRepository.findAll(pageable);
    }

    public Appointment findById(String appointmentId) {
        return appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ObjectNotFoundException("appointment", appointmentId));
    }

    public List<Appointment> findByStatus(String status) {
        return appointmentRepository.findByStatus(status);
    }

    public List<Appointment> findByDoctorId(String doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    public List<Appointment> findByUserId(String userId) {
        return appointmentRepository.findByUserId(userId);
    }

    public Appointment save(Appointment appointment) {
        if (appointment.getTimeslots() != null) {
            appointment.getTimeslots().forEach(timeslot -> {
                if (timeslot.getAppointment() == null) {
                    timeslot.setAppointment(appointment);
                }
            });
        }
        return appointmentRepository.save(appointment);
    }

    public Appointment update(String appointmentId, Appointment appointment) {
        return appointmentRepository.findById(appointmentId)
                .map(existingAppointment -> {
                    existingAppointment.setDescription(appointment.getDescription());
                    existingAppointment.setStatus(appointment.getStatus());
                    existingAppointment.setDoctor(appointment.getDoctor());
                    existingAppointment.setUser(appointment.getUser());

                    if (appointment.getTimeslots() != null) {
                        if (existingAppointment.getTimeslots() != null) {
                            existingAppointment.getTimeslots().clear();
                        }
                        appointment.getTimeslots().forEach(timeslot -> {
                            timeslot.setAppointment(existingAppointment);
                            existingAppointment.getTimeslots().add(timeslot);
                        });
                    }

                    return appointmentRepository.save(existingAppointment);
                })
                .orElseThrow(() -> new ObjectNotFoundException("appointment", appointmentId));
    }

    public void delete(String appointmentId) {
        appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ObjectNotFoundException("appointment", appointmentId));
        appointmentRepository.deleteById(appointmentId);
    }
}