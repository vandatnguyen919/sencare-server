package org.entrepremium.sencare.features.hospital;

import jakarta.transaction.Transactional;
import org.entrepremium.sencare.system.exception.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public Page<Hospital> findAll(Pageable pageable) {
        return hospitalRepository.findAll(pageable);
    }

    public Hospital findById(String hospitalId) {
        return hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new ObjectNotFoundException("hospital", hospitalId));
    }

    public List<Hospital> findByName(String name) {
        return hospitalRepository.findByHospitalNameContaining(name);
    }

    public Hospital save(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    public Hospital update(String hospitalId, Hospital hospital) {
        return hospitalRepository.findById(hospitalId)
                .map(existingHospital -> {
                    existingHospital.setHospitalAvatar(hospital.getHospitalAvatar());
                    existingHospital.setHospitalName(hospital.getHospitalName());
                    existingHospital.setHospitalDescription(hospital.getHospitalDescription());
                    existingHospital.setHospitalPhone(hospital.getHospitalPhone());
                    existingHospital.setUser(hospital.getUser());
                    return hospitalRepository.save(existingHospital);
                })
                .orElseThrow(() -> new ObjectNotFoundException("hospital", hospitalId));
    }

    public void delete(String hospitalId) {
        hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new ObjectNotFoundException("hospital", hospitalId));
        hospitalRepository.deleteById(hospitalId);
    }
}
