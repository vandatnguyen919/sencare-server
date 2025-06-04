package org.entrepremium.sencare.features.education;

import jakarta.transaction.Transactional;
import org.entrepremium.sencare.system.exception.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EducationService {

    private final EducationRepository educationRepository;

    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    public Page<Education> findAll(Pageable pageable) {
        return educationRepository.findAll(pageable);
    }

    public Education findById(String eduId) {
        return educationRepository.findById(eduId)
                .orElseThrow(() -> new ObjectNotFoundException("education", eduId));
    }

    public List<Education> findByDoctor(String doctorId) {
        return educationRepository.findByDoctorDoctorId(doctorId);
    }

    public List<Education> findByYear(Integer year) {
        return educationRepository.findByIssuedYear(year);
    }

    public Education save(Education education) {
        return educationRepository.save(education);
    }

    public Education update(String eduId, Education education) {
        return educationRepository.findById(eduId)
                .map(existingEducation -> {
                    existingEducation.setCollegeName(education.getCollegeName());
                    existingEducation.setDescription(education.getDescription());
                    existingEducation.setIssuedBy(education.getIssuedBy());
                    existingEducation.setIssuedYear(education.getIssuedYear());
                    existingEducation.setDoctor(education.getDoctor());
                    return educationRepository.save(existingEducation);
                })
                .orElseThrow(() -> new ObjectNotFoundException("education", eduId));
    }

    public void delete(String eduId) {
        educationRepository.findById(eduId)
                .orElseThrow(() -> new ObjectNotFoundException("education", eduId));
        educationRepository.deleteById(eduId);
    }
}