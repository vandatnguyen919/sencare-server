package org.entrepremium.sencare.features.doctorsystem.education;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education, String> {
    Page<Education> findAll(Pageable pageable);

    List<Education> findByDoctorDoctorId(String doctorId);

    List<Education> findByIssuedYear(Integer year);
}