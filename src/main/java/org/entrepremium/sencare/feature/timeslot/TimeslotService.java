package org.entrepremium.sencare.feature.timeslot;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.entrepremium.sencare.system.exception.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TimeslotService {

    private final TimeslotRepository timeslotRepository;

    public Page<Timeslot> findAll(Pageable pageable) {
        return timeslotRepository.findAll(pageable);
    }

    public Timeslot findById(String timeslotId) {
        return timeslotRepository.findById(timeslotId)
                .orElseThrow(() -> new ObjectNotFoundException("timeslot", timeslotId));
    }

    public List<Timeslot> findByDoctorId(String doctorId) {
        return timeslotRepository.findByDoctorId(doctorId);
    }

    public List<Timeslot> findByDate(LocalDate date) {
        return timeslotRepository.findByDate(date);
    }

    public List<Timeslot> findByOccupiedStatus(boolean isOccupied) {
        return timeslotRepository.findByOccupiedStatus(isOccupied);
    }

    public List<Timeslot> findByDoctorIdAndDate(String doctorId, LocalDate date) {
        return timeslotRepository.findByDoctorIdAndDate(doctorId, date);
    }

    public Timeslot save(Timeslot timeslot) {
        return timeslotRepository.save(timeslot);
    }

    public Timeslot update(String timeslotId, Timeslot timeslot) {
        return timeslotRepository.findById(timeslotId)
                .map(existingTimeslot -> {
                    existingTimeslot.setStartTime(timeslot.getStartTime());
                    existingTimeslot.setEndTime(timeslot.getEndTime());
                    existingTimeslot.setDate(timeslot.getDate());
                    existingTimeslot.setOccupied(timeslot.isOccupied());
                    existingTimeslot.setDoctor(timeslot.getDoctor());
                    existingTimeslot.setHosServ(timeslot.getHosServ());

                    // Added: Handle appointment relationship
                    existingTimeslot.setAppointment(timeslot.getAppointment());

                    return timeslotRepository.save(existingTimeslot);
                })
                .orElseThrow(() -> new ObjectNotFoundException("timeslot", timeslotId));
    }

    public void delete(String timeslotId) {
        timeslotRepository.findById(timeslotId)
                .orElseThrow(() -> new ObjectNotFoundException("timeslot", timeslotId));
        timeslotRepository.deleteById(timeslotId);
    }

    public Timeslot assignToAppointment(String timeslotId, String appointmentId) {
        Timeslot timeslot = findById(timeslotId);
        timeslot.setOccupied(true);
        return timeslotRepository.save(timeslot);
    }

    public Timeslot removeFromAppointment(String timeslotId) {
        Timeslot timeslot = findById(timeslotId);
        timeslot.setAppointment(null);
        timeslot.setOccupied(false);
        return timeslotRepository.save(timeslot);
    }
}