package org.entrepremium.sencare.features.specialization;

import jakarta.transaction.Transactional;
import org.entrepremium.sencare.system.exception.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SpecializationService {

    private final SpecializationRepository specializationRepository;

    public SpecializationService(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    public Page<Specialization> findAll(Pageable pageable) {
        return specializationRepository.findAll(pageable);
    }

    public Specialization findById(String specId) {
        return specializationRepository.findById(specId)
                .orElseThrow(() -> new ObjectNotFoundException("specialization", specId));
    }

    public List<Specialization> findByName(String name) {
        return specializationRepository.findBySpecNameContaining(name);
    }

    public Specialization save(Specialization specialization) {
        return specializationRepository.save(specialization);
    }

    public Specialization update(String specId, Specialization specialization) {
        return specializationRepository.findById(specId)
                .map(existingSpecialization -> {
                    existingSpecialization.setSpecName(specialization.getSpecName());
                    existingSpecialization.setSpecDescription(specialization.getSpecDescription());
                    return specializationRepository.save(existingSpecialization);
                })
                .orElseThrow(() -> new ObjectNotFoundException("specialization", specId));
    }

    public void delete(String specId) {
        specializationRepository.findById(specId)
                .orElseThrow(() -> new ObjectNotFoundException("specialization", specId));
        specializationRepository.deleteById(specId);
    }
}