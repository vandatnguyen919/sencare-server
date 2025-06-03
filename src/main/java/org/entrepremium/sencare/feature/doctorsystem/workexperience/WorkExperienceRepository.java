package org.entrepremium.sencare.feature.doctorsystem.workexperience;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience, String> {

    List<WorkExperience> findByDoctorDoctorId(String doctorId);

    List<WorkExperience> findByHospitalNameContaining(String hospitalName);

    List<WorkExperience> findByJobTitleContaining(String jobTitle);

    @Query("SELECT w FROM WorkExperience w WHERE w.startDate >= :startDate")
    List<WorkExperience> findByStartDateAfter(@Param("startDate") LocalDate startDate);

    @Query("SELECT w FROM WorkExperience w WHERE w.endDate IS NULL")
    List<WorkExperience> findCurrentExperiences();
}