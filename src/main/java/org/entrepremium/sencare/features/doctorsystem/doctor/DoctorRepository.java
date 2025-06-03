package org.entrepremium.sencare.features.doctorsystem.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, String> {
    Page<Doctor> findAll(Pageable pageable);

    @Query("SELECT d FROM Doctor d WHERE d.doctorName LIKE %:name%")
    List<Doctor> findByDoctorNameContaining(@Param("name") String name);

    List<Doctor> findByDoctorPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}
