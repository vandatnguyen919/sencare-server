package org.entrepremium.sencare.features.workexperience;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience, String> {
    Page<WorkExperience> findAll(Pageable pageable);

    List<WorkExperience> findByDoctorDoctorId(String doctorId);

    List<WorkExperience> findByJobTitleContaining(String jobTitle);

    @Query("SELECT w FROM WorkExperience w WHERE w.startDate >= :startDate")
    List<WorkExperience> findByStartDateAfter(@Param("startDate") LocalDate startDate);

    @Query("SELECT w FROM WorkExperience w WHERE w.endDate IS NULL")
    List<WorkExperience> findCurrentExperiences();
}