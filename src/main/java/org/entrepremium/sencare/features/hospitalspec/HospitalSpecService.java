package org.entrepremium.sencare.features.hospitalspec;

import jakarta.transaction.Transactional;
import org.entrepremium.sencare.system.exception.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class HospitalSpecService {

    private final HospitalSpecRepository hospitalSpecRepository;

    public HospitalSpecService(HospitalSpecRepository hospitalSpecRepository) {
        this.hospitalSpecRepository = hospitalSpecRepository;
    }

    public Page<HospitalSpec> findAll(Pageable pageable) {
        return hospitalSpecRepository.findAll(pageable);
    }

    public HospitalSpec findById(String id) {
        return hospitalSpecRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("hospitalSpec", id));
    }

    public List<HospitalSpec> findByHospitalId(String hospitalId) {
        return hospitalSpecRepository.findByHospitalHospitalId(hospitalId);
    }

    public List<HospitalSpec> findBySpecializationId(String specId) {
        return hospitalSpecRepository.findBySpecializationSpecId(specId);
    }

    public List<HospitalSpec> findByHospitalName(String hospitalName) {
        return hospitalSpecRepository.findByHospitalName(hospitalName);
    }

    public List<HospitalSpec> findBySpecializationName(String specName) {
        return hospitalSpecRepository.findBySpecializationName(specName);
    }

    public HospitalSpec save(HospitalSpec hospitalSpec) {
        return hospitalSpecRepository.save(hospitalSpec);
    }

    public HospitalSpec update(String id, HospitalSpec hospitalSpec) {
        return hospitalSpecRepository.findById(id)
                .map(existingHospitalSpec -> {
                    existingHospitalSpec.setHospital(hospitalSpec.getHospital());
                    existingHospitalSpec.setSpecialization(hospitalSpec.getSpecialization());
                    return hospitalSpecRepository.save(existingHospitalSpec);
                })
                .orElseThrow(() -> new ObjectNotFoundException("hospitalSpec", id));
    }

    public void delete(String id) {
        hospitalSpecRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("hospitalSpec", id));
        hospitalSpecRepository.deleteById(id);
    }
}
