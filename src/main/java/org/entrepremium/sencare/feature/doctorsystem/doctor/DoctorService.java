package org.entrepremium.sencare.feature.doctorsystem.doctor;

import jakarta.transaction.Transactional;
import org.entrepremium.sencare.system.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    public Doctor findById(String doctorId) {
        return doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ObjectNotFoundException("doctor", doctorId));
    }

    public List<Doctor> findByName(String name) {
        return doctorRepository.findByDoctorNameContaining(name);
    }

    public List<Doctor> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return doctorRepository.findByDoctorPriceBetween(minPrice, maxPrice);
    }

    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor update(String doctorId, Doctor doctor) {
        return doctorRepository.findById(doctorId)
                .map(existingDoctor -> {
                    existingDoctor.setDoctorAvatar(doctor.getDoctorAvatar());
                    existingDoctor.setDoctorName(doctor.getDoctorName());
                    existingDoctor.setDoctorDescription(doctor.getDoctorDescription());
                    existingDoctor.setDoctorPrice(doctor.getDoctorPrice());
                    existingDoctor.setHospital(doctor.getHospital());
                    return doctorRepository.save(existingDoctor);
                })
                .orElseThrow(() -> new ObjectNotFoundException("doctor", doctorId));
    }

    public void delete(String doctorId) {
        doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ObjectNotFoundException("doctor", doctorId));
        doctorRepository.deleteById(doctorId);
    }
}
