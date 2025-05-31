package org.entrepremium.sencare.features.doctorsystem.education;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education, String> {

    List<Education> findByDoctorDoctorId(String doctorId);

    List<Education> findByCollegeNameContaining(String collegeName);

    List<Education> findByIssuedYear(Integer year);
}