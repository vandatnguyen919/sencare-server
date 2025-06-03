package org.entrepremium.sencare.feature.doctorsystem.workexperience;

import jakarta.transaction.Transactional;
import org.entrepremium.sencare.system.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class WorkExperienceService {

    private final WorkExperienceRepository workExperienceRepository;

    public WorkExperienceService(WorkExperienceRepository workExperienceRepository) {
        this.workExperienceRepository = workExperienceRepository;
    }

    public List<WorkExperience> findAll() {
        return workExperienceRepository.findAll();
    }

    public WorkExperience findById(String wexId) {
        return workExperienceRepository.findById(wexId)
                .orElseThrow(() -> new ObjectNotFoundException("work experience", wexId));
    }

    public List<WorkExperience> findByDoctor(String doctorId) {
        return workExperienceRepository.findByDoctorDoctorId(doctorId);
    }

    public List<WorkExperience> findByHospital(String hospitalName) {
        return workExperienceRepository.findByHospitalNameContaining(hospitalName);
    }

    public List<WorkExperience> findByJobTitle(String jobTitle) {
        return workExperienceRepository.findByJobTitleContaining(jobTitle);
    }

    public List<WorkExperience> findByStartDateAfter(LocalDate startDate) {
        return workExperienceRepository.findByStartDateAfter(startDate);
    }

    public List<WorkExperience> findCurrentExperiences() {
        return workExperienceRepository.findCurrentExperiences();
    }

    public WorkExperience save(WorkExperience workExperience) {
        return workExperienceRepository.save(workExperience);
    }

    public WorkExperience update(String wexId, WorkExperience workExperience) {
        return workExperienceRepository.findById(wexId)
                .map(existingWorkExperience -> {
                    existingWorkExperience.setHospitalName(workExperience.getHospitalName());
                    existingWorkExperience.setJobTitle(workExperience.getJobTitle());
                    existingWorkExperience.setDescription(workExperience.getDescription());
                    existingWorkExperience.setStartDate(workExperience.getStartDate());
                    existingWorkExperience.setEndDate(workExperience.getEndDate());
                    existingWorkExperience.setDoctor(workExperience.getDoctor());
                    return workExperienceRepository.save(existingWorkExperience);
                })
                .orElseThrow(() -> new ObjectNotFoundException("work experience", wexId));
    }

    public void delete(String wexId) {
        workExperienceRepository.findById(wexId)
                .orElseThrow(() -> new ObjectNotFoundException("work experience", wexId));
        workExperienceRepository.deleteById(wexId);
    }
}
